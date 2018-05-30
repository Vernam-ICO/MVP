package com.dimiroma.vernam.areas.user.services;

import com.dimiroma.vernam.areas.role.entity.Role;
import com.dimiroma.vernam.areas.user.entity.User;
import com.dimiroma.vernam.areas.user.models.bindingModels.UserBindingModel;
import com.dimiroma.vernam.areas.user.models.viewModels.UserViewModel;
import com.dimiroma.vernam.areas.user.repository.UserRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User findById(Long id) throws NotFoundException {
        if (!this.userRepository.exists(id)) {
            throw new NotFoundException("Invalid User!");
        }
        return this.userRepository.findOne(id);
    }

    @Override
    public UserViewModel register(UserBindingModel bindingModel, Role role) {
        User user = this.modelMapper.map(bindingModel, User.class);
        user.setActive(true);
        user.addRole(role);
        this.userRepository.saveAndFlush(user);
        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        return userViewModel;
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public UserViewModel getCurrentUser() {
        User user = this.getCurrentUserEntity();
        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        return userViewModel;
    }

    @Override
    public Page<UserViewModel> list(Pageable pageable, boolean active) {
        Page<UserViewModel> userViewModels = this.userRepository.findAllByActiveOrderByIdAsc(pageable, active)
                .map(new Converter<User, UserViewModel>() {
                    @Override
                    public UserViewModel convert(User user) {
                        UserViewModel viewModel = new UserViewModel();
                        viewModel.setId(user.getId());
                        viewModel.setEmail(user.getEmail());
                        viewModel.setFirstName(user.getFirstName());
                        viewModel.setLastName(user.getLastName());
                        viewModel.setPhone(user.getPhone());
                        viewModel.setAddress(user.getAddress());
                        viewModel.setTokens(user.getTokens());
                        return viewModel;
                    }
                });
        return userViewModels;
    }

    @Override
    public UserViewModel edit(final UserBindingModel bindingModel) throws NotFoundException {
        if (!this.userRepository.exists(bindingModel.getId())) {
            throw new NotFoundException("Invalid User!");
        }
        User user = this.userRepository.findOne(bindingModel.getId());

        user.setFirstName(bindingModel.getFirstName());
        user.setLastName(bindingModel.getLastName());
        user.setPhone(bindingModel.getPhone());
        user.setAddress(bindingModel.getAddress());
        user.setPassword(bindingModel.getPassword());

        this.userRepository.saveAndFlush(user);

        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        return userViewModel;
    }

    @Override
    public UserViewModel delete(User user) {
        user.setActive(false);
        this.userRepository.saveAndFlush(user);
        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        return userViewModel;
    }

    @Override
    public Long tokensTransfer(Long amount) {
        User user = this.getCurrentUserEntity();
        if (amount > user.getTokens()) {
            throw new RuntimeException("Not enough tokens for transfer!");
        }
        user.setTokens(user.getTokens() - amount);
        this.userRepository.saveAndFlush(user);
        return user.getTokens();
    }

    private List<UserViewModel> convertToViewModels(List<User> users) {
        List<UserViewModel> userViewModels = new ArrayList<>();
        for (User user : users) {
            UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
            userViewModels.add(userViewModel);
        }
        return userViewModels;
    }

    //Get current logged user as entity
    private User getCurrentUserEntity() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepository.findByEmail(principal.getUsername());
        return user;
    }
}
