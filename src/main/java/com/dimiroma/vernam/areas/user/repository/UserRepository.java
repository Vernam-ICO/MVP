package com.dimiroma.vernam.areas.user.repository;

import com.dimiroma.vernam.areas.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(final String email);
    List<User> findAllByOrderByIdAsc();
    Page<User> findAllByActiveOrderByIdAsc(Pageable pageable, boolean active);
}
