package com.dimiroma.vernam.areas.user.controller;

import com.dimiroma.vernam.areas.payment.dto.PaymentViewModel;
import com.dimiroma.vernam.areas.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-wallet")
public class UserWalletController {
    private UserService userService;

    @Autowired
    public UserWalletController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/last-payment")
    public PaymentViewModel paymentViewModel() {

        // TODO return the latest payment for the current user
        return new PaymentViewModel();
    }
}
