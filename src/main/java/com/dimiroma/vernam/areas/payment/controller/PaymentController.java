package com.dimiroma.vernam.areas.payment.controller;

import com.dimiroma.vernam.areas.payment.dto.PaymentCreateViewModel;
import com.dimiroma.vernam.areas.payment.dto.PaymentViewModel;
import com.dimiroma.vernam.areas.payment.service.PaymentService;
import com.dimiroma.vernam.areas.user.entity.User;
import com.dimiroma.vernam.areas.user.services.UserService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentService paymentService;
    private UserService userService;

    @Autowired
    public PaymentController(final PaymentService paymentService,
                             final UserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public List<PaymentViewModel> listAll() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.findByEmail(userDetails.getUsername());


        return this.paymentService.getAll(user);
    }

    @PostMapping("/create")
    public PaymentViewModel create(@RequestBody final PaymentCreateViewModel paymentCreateViewModel) throws MessagingException, IOException, DocumentException {
        return this.paymentService.create(paymentCreateViewModel);
    }
}
