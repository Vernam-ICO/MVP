package com.dimiroma.vernam.areas.payment.service;

import com.dimiroma.vernam.areas.payment.dto.PaymentCreateViewModel;
import com.dimiroma.vernam.areas.payment.dto.PaymentViewModel;
import com.dimiroma.vernam.areas.user.entity.User;
import com.itextpdf.text.DocumentException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface PaymentService {
    List<PaymentViewModel> getAll(final User user);

    PaymentViewModel create(final PaymentCreateViewModel paymentCreateViewModel) throws MessagingException, IOException, DocumentException;
}
