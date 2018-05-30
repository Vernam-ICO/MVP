package com.dimiroma.vernam.areas.user.controller;


import com.dimiroma.vernam.areas.role.entity.Role;
import com.dimiroma.vernam.areas.role.services.RoleService;
import com.dimiroma.vernam.areas.user.entity.User;
import com.dimiroma.vernam.areas.user.models.bindingModels.UserBindingModel;
import com.dimiroma.vernam.areas.user.models.viewModels.UserViewModel;
import com.dimiroma.vernam.areas.user.services.UserService;
import com.dimiroma.vernam.config.auth.AuthenticationUtils;
import com.dimiroma.vernam.config.auth.LoginRequest;
import com.dimiroma.vernam.config.auth.LoginResponse;
import com.dimiroma.vernam.config.jwt.JWTConfigurer;
import com.dimiroma.vernam.config.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class AuthUserController {
    private final UserService userService;
    private final RoleService roleService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationUtils authenticationUtils;


    @Autowired
    public AuthUserController(UserService userService,
                              RoleService roleService,
                              TokenProvider tokenProvider,
                              AuthenticationManager authenticationManager, AuthenticationUtils authenticationUtils) {
        this.userService = userService;
        this.roleService = roleService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.authenticationUtils = authenticationUtils;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody final LoginRequest loginRequest, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        UserDetails principal = this.authenticationUtils.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginRequest.isRememberMe()) ? false : loginRequest.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        User user = this.userService.findByEmail(principal.getUsername());
        return new LoginResponse(user.getId(), user.getEmail(), user.isAdmin(), jwt);
    }

    @PostMapping("/register")
    public UserViewModel register(@RequestBody final UserBindingModel userBindingModel){
        Role role = this.roleService.getUserRole();
        return this.userService.register(userBindingModel, role);
    }
}
