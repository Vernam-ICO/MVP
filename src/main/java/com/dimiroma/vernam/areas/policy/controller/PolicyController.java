package com.dimiroma.vernam.areas.policy.controller;

import com.dimiroma.vernam.areas.policy.service.PolicyService;
import com.dimiroma.vernam.enums.PolicyType;
import com.dimiroma.vernam.areas.policy.entity.Policy;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.GroupData;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.TravelInsuranceMetaModel;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.TripData;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.enums.GroupType;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.enums.PurposeType;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.enums.TripType;
import com.dimiroma.vernam.areas.policy.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/policy")
public class PolicyController {
    private PolicyService policyService;

    @Autowired
    public PolicyController(final PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/retrieve-pdf/{policyId}")
    public ResponseEntity<InputStreamResource> getPdf(@PathVariable("policyId") Long policyId) throws IOException {

        InputStream targetStream = this.policyService.getPdfById(policyId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(targetStream));
    }
}
