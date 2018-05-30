package com.dimiroma.vernam.areas.role.services;

import com.dimiroma.vernam.enums.RoleType;
import com.dimiroma.vernam.areas.role.entity.Role;
import com.dimiroma.vernam.areas.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getUserRole() {
        Role role = this.roleRepository.findByName(RoleType.ROLE_USER);

        if(role == null){
            role = new Role(RoleType.ROLE_USER);
            this.roleRepository.save(role);
        }
        return role;
    }
}
