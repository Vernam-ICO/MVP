package com.dimiroma.vernam.areas.policy.service;

import com.dimiroma.vernam.areas.policy.entity.Policy;
import com.dimiroma.vernam.areas.policy.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class PolicyServiceImpl implements PolicyService {
    private PolicyRepository policyRepository;

    @Autowired
    public PolicyServiceImpl(final PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public InputStream getPdfById(final Long id) {
        Policy policy = this.policyRepository.findOne(id);

        InputStream targetStream = new ByteArrayInputStream(policy.getData());

        return targetStream;
    }
}
