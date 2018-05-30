package com.dimiroma.vernam.areas.user.controller;

import com.dimiroma.vernam.areas.user.entity.User;
import com.dimiroma.vernam.areas.user.models.bindingModels.UserBindingModel;
import com.dimiroma.vernam.areas.user.models.viewModels.UserViewModel;
import com.dimiroma.vernam.areas.user.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    private final static boolean ACTIVE_USERS = true;

    private final UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Page<UserViewModel> getAllUsers(Pageable pageable){
        return this.userService.list(pageable, ACTIVE_USERS);
    }

    @PostMapping("/edit")
    public UserViewModel editUser(final @RequestBody UserBindingModel userBindingModel) throws NotFoundException {
        UserViewModel userViewModel = this.userService.edit(userBindingModel);
        return userViewModel;
    }

    @GetMapping("/delete/{id}")
    public UserViewModel deleteUser(final @PathVariable("id") Long id) throws NotFoundException {
        User user = this.userService.findById(id);
        UserViewModel userViewModel = this.userService.delete(user);
        return userViewModel;
    }
}
