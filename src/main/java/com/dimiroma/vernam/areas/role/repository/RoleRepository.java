package com.dimiroma.vernam.areas.role.repository;

import com.dimiroma.vernam.enums.RoleType;
import com.dimiroma.vernam.areas.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(final RoleType roleType);
}
