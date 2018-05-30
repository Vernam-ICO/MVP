package com.dimiroma.vernam.areas.payment.dto;

import java.io.Serializable;

public class PaymentViewModel implements Serializable {
    private String date;
    private Long policyId;
    private Long tokens;
    private String expirationDate;

    public PaymentViewModel() {
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public Long getPolicyId() {
        return this.policyId;
    }

    public void setPolicyId(final Long policyId) {
        this.policyId = policyId;
    }

    public Long getTokens() {
        return this.tokens;
    }

    public void setTokens(final Long tokens) {
        this.tokens = tokens;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(final String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
