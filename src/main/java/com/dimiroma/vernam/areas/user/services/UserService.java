package com.dimiroma.vernam.areas.user.services;

import com.dimiroma.vernam.areas.role.entity.Role;
import com.dimiroma.vernam.areas.user.entity.User;
import com.dimiroma.vernam.areas.user.models.bindingModels.UserBindingModel;
import com.dimiroma.vernam.areas.user.models.viewModels.UserViewModel;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findById(Long id) throws NotFoundException;

    //Auth
    UserViewModel register(UserBindingModel bindingModel, Role role);
    User findByEmail(String email);

    UserViewModel getCurrentUser();
    Page<UserViewModel> list(Pageable pageable, boolean active);

    UserViewModel edit(final UserBindingModel bindingModel) throws NotFoundException;
    UserViewModel delete(User user);

    Long tokensTransfer(Long amount);
}
