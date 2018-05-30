package com.dimiroma.vernam.areas.payment.service;

import com.dimiroma.vernam.areas.payment.dto.PaymentCreateViewModel;
import com.dimiroma.vernam.areas.payment.dto.PaymentViewModel;
import com.dimiroma.vernam.areas.payment.entity.Payment;
import com.dimiroma.vernam.areas.payment.repository.PaymentRepository;
import com.dimiroma.vernam.areas.policy.entity.Policy;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.GroupData;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.TravelInsuranceMetaModel;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.TripData;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.enums.GroupType;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.enums.TripType;
import com.dimiroma.vernam.areas.policy.repository.PolicyRepository;
import com.dimiroma.vernam.areas.policy.service.PolicyService;
import com.dimiroma.vernam.areas.role.services.RoleService;
import com.dimiroma.vernam.areas.smtp.services.SmtpService;
import com.dimiroma.vernam.areas.user.dto.UserPaymentModel;
import com.dimiroma.vernam.areas.user.entity.User;
import com.dimiroma.vernam.areas.user.repository.UserRepository;
import com.dimiroma.vernam.enums.PolicyType;
import com.dimiroma.vernam.utils.CryptoUtils;
import com.dimiroma.vernam.utils.passwordGenerator.PasswordGeneratorService;
import com.dimiroma.vernam.utils.pdfGenerator.PdfGeneratorService;
import com.itextpdf.text.DocumentException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private PolicyRepository policyRepository;
    private UserRepository userRepository;
    private SmtpService smtpService;
    private RoleService roleService;
    private PdfGeneratorService pdfService;
    private PolicyService policyService;
    private PasswordGeneratorService passwordGeneratorService;
    private ModelMapper modelMapper;

    @Autowired
    public PaymentServiceImpl(final PaymentRepository paymentRepository,
                              final PolicyRepository policyRepository,
                              final UserRepository userRepository,
                              final SmtpService smtpService,
                              final RoleService roleService,
                              final PdfGeneratorService pdfService,
                              final PolicyService policyService,
                              final PasswordGeneratorService passwordGeneratorService,
                              final ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
        this.smtpService = smtpService;
        this.roleService = roleService;
        this.pdfService = pdfService;
        this.policyService = policyService;
        this.passwordGeneratorService = passwordGeneratorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PaymentViewModel> getAll(final User user) {
        List<Payment> payments = this.paymentRepository.findAllByUserOrderByCreationDateDesc(user);

        return this.convertPaymentsToViewModels(payments);
    }

    @Override
    public PaymentViewModel create(final PaymentCreateViewModel paymentCreateViewModel) throws MessagingException, IOException, DocumentException {
        PaymentCreateViewModel validatedViewModel = this.validatePaymentCreateViewModel(paymentCreateViewModel);
        // 01.Check if user exists
        // if false create and send e-mail with password
        UserPaymentModel inputUser = paymentCreateViewModel.getUser();
        User user = this.userRepository.findByEmail(inputUser.getEmail().toLowerCase());

        // 02.Generate Pdf
        byte[] fileBytes = pdfService.generatePdf(validatedViewModel);

        if (user == null) {
            String genPassword = this.passwordGeneratorService.generatePassword(6);
            user = this.createUser(validatedViewModel.getUser(),genPassword);
            String messageBody = "You can login to your demo account with\n" +
                    "this email and password " + genPassword +
            "\nTHIS IS DEMO ACCOUNT";
            this.smtpService.send(user.getEmail(), "Vernam DEMO ACCOUNT CREATED", messageBody);
        }

        user.addTokens(paymentCreateViewModel.getTokens());
        this.userRepository.saveAndFlush(user);


        // 03.Create policy
        TravelInsuranceMetaModel travelInsuranceMetaModel = modelMapper.map(paymentCreateViewModel, TravelInsuranceMetaModel.class);
        Policy policy = new Policy();
        policy.setTokens(paymentCreateViewModel.getTokens());
        policy.setInsuranceAmount(paymentCreateViewModel.getInsuranceAmount());
        policy.setPolicyType(PolicyType.TRAVEL_POLICY);
        policy.setPolicyMetaData(travelInsuranceMetaModel);
        policy.setCreationDate(new Date());
        policy.setExpirationDate(new Date());
        policy.setData(fileBytes); // TODO : build pdf

        this.policyRepository.saveAndFlush(policy);

        // 04.Create payment and return it as view model
        Payment payment = new Payment();
        payment.setCreationDate(new Date());
        payment.setUser(user);
        payment.setPolicy(policy);

        this.paymentRepository.saveAndFlush(payment);

        // 05.Send attached file to user email
        String messageBody = "Generated PDF File . THIS IS A DEMO !";
        String messageSubject = "Vernam DEMO Policy Created!";
        this.smtpService.send(user.getEmail(), messageSubject, messageBody, this.policyService.getPdfById(policy.getId()));

        return this.convertSinglePaymentToViewModel(payment);
    }

    private User createUser(final UserPaymentModel userPaymentModel, final String genPassword) {
        User user = new User();
        user.setEmail(userPaymentModel.getEmail().toLowerCase());
        user.setFirstName(userPaymentModel.getFirstName());
        user.setLastName(userPaymentModel.getLastName());
        user.setAddress(userPaymentModel.getAddress());
        user.setPhone(userPaymentModel.getPhone());
        user.setPassword(CryptoUtils.encodePassword(genPassword));
        user.addRole(this.roleService.getUserRole());
        user.setActive(true);

        return this.userRepository.save(user);
    }

    private PaymentCreateViewModel validatePaymentCreateViewModel(PaymentCreateViewModel vm) {
        TripData inputTripData = vm.getTripData();
        TripData validatedTripData = new TripData();

        if (vm.getTripType().equals(TripType.SINGLE_TRIP)) {

            validatedTripData.setFrom(inputTripData.getFrom());
            validatedTripData.setTo(inputTripData.getTo());
        } else {
            validatedTripData.setPeriod(inputTripData.getPeriod());
            validatedTripData.setStartDate(inputTripData.getStartDate());
        }
        vm.setTripData(validatedTripData);

        GroupData inputGroupData = vm.getGroupData();
        GroupData validatedGroupData = vm.getGroupData();

        if (vm.getGroupType().equals(GroupType.SINGLE_PERSON)) {
            validatedGroupData.setAge(inputGroupData.getAge());
        } else {
            validatedGroupData.setChildren(inputGroupData.getChildren());
            validatedGroupData.setAdults(inputGroupData.getAdults());
            validatedGroupData.setYoungSeniors(inputGroupData.getYoungSeniors());
            validatedGroupData.setMiddleSeniors(inputGroupData.getMiddleSeniors());
            validatedGroupData.setOldSeniors(inputGroupData.getOldSeniors());
        }
        vm.setGroupData(validatedGroupData);

        return vm;
    }

    private List<PaymentViewModel> convertPaymentsToViewModels(final List<Payment> payments) {
        List<PaymentViewModel> result = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentViewModel current = this.convertSinglePaymentToViewModel(payment);

            result.add(current);
        }

        return result;
    }

    private PaymentViewModel convertSinglePaymentToViewModel(final Payment payment) {
        PaymentViewModel vm = new PaymentViewModel();

        vm.setDate(payment.getCreationDate().toString());
        vm.setExpirationDate(payment.getPolicy().getExpirationDate().toString());
        vm.setPolicyId(payment.getPolicy().getId());
        vm.setTokens(payment.getPolicy().getTokens());

        return vm;
    }
}
