package com.dimiroma.vernam.areas.user.controller;

import com.dimiroma.vernam.areas.user.entity.User;
import com.dimiroma.vernam.areas.user.models.bindingModels.UserBindingModel;
import com.dimiroma.vernam.areas.user.models.viewModels.UserViewModel;
import com.dimiroma.vernam.areas.user.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current")
    public UserViewModel getCurrentUser(){
        UserViewModel userViewModel = this.userService.getCurrentUser();
        return userViewModel;
    }

    @GetMapping("/tokens-transfer/amount/{amount}")
    public Long tokensTransfer(final @PathVariable("amount") Long amount){
        return this.userService.tokensTransfer(amount);
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public UserViewModel editUser(final @RequestBody UserBindingModel userBindingModel) throws NotFoundException {
        UserViewModel userViewModel = this.userService.edit(userBindingModel);
        return userViewModel;
    }

}
