package com.dimiroma.vernam.areas.payment.repository;

import com.dimiroma.vernam.areas.payment.entity.Payment;
import com.dimiroma.vernam.areas.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByUserOrderByCreationDateDesc(final User user);
}
