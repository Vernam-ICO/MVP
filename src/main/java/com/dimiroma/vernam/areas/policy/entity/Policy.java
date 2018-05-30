package com.dimiroma.vernam.areas.policy.entity;

import com.dimiroma.vernam.enums.PolicyType;
import com.dimiroma.vernam.areas.payment.entity.Payment;
import com.dimiroma.vernam.areas.policy.policyMetaModels.PolicyMetaModel;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.TravelInsuranceMetaType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "policies")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "travelInsuranceMeta", typeClass = TravelInsuranceMetaType.class)
})
public class Policy {
    private Long id;
    private PolicyType policyType;
    private PolicyMetaModel policyMetaData;
    private byte[] data;
    private double insuranceAmount;
    private Long tokens;
    private Date creationDate;
    private Date expirationDate;
    private Set<Payment> payments;

    public Policy() {
        this.creationDate = new Date();

        this.payments = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "policyType", nullable = false)
    public PolicyType getPolicyType() {
        return this.policyType;
    }

    public void setPolicyType(final PolicyType policyType) {
        this.policyType = policyType;
    }

    @Type(type = "travelInsuranceMeta")
    @Column(name = "policyMetaData", nullable = false, columnDefinition = "jsonb")
    public PolicyMetaModel getPolicyMetaData() {
        return this.policyMetaData;
    }

    public void setPolicyMetaData(final PolicyMetaModel policyMetaData) {
        this.policyMetaData = policyMetaData;
    }

    @Column(name = "data")
    public byte[] getData() {
        return this.data;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    @Column(name = "insuranceAmount", nullable = false)
    public double getInsuranceAmount() {
        return this.insuranceAmount;
    }

    public void setInsuranceAmount(final double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    @Column(name = "tokens")
    public Long getTokens() {
        return this.tokens;
    }

    public void setTokens(final Long tokens) {
        this.tokens = tokens;
    }

    @Column(name = "creationDate", nullable = false)
    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "expirationDate")
    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @OneToMany(mappedBy = "policy")
    public Set<Payment> getPayments() {
        return this.payments;
    }

    public void setPayments(final Set<Payment> payments) {
        this.payments = payments;
    }
}
