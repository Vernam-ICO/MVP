package com.dimiroma.vernam.areas.policy.repository;

import com.dimiroma.vernam.areas.policy.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
