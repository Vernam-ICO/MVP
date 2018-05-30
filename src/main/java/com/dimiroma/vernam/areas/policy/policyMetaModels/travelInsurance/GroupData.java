package com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance;

import java.io.Serializable;

public class GroupData implements Serializable {
    private Long age;

    private Long children;
    private Long adults;
    private Long youngSeniors;
    private Long middleSeniors;
    private Long oldSeniors;

    public GroupData() {
    }

    public Long getAge() {
        return this.age;
    }

    public void setAge(final Long age) {
        this.age = age;
    }

    public Long getChildren() {
        return this.children;
    }

    public void setChildren(final Long children) {
        this.children = children;
    }

    public Long getAdults() {
        return this.adults;
    }

    public void setAdults(final Long adults) {
        this.adults = adults;
    }

    public Long getYoungSeniors() {
        return this.youngSeniors;
    }

    public void setYoungSeniors(final Long youngSeniors) {
        this.youngSeniors = youngSeniors;
    }

    public Long getMiddleSeniors() {
        return this.middleSeniors;
    }

    public void setMiddleSeniors(final Long middleSeniors) {
        this.middleSeniors = middleSeniors;
    }

    public Long getOldSeniors() {
        return this.oldSeniors;
    }

    public void setOldSeniors(final Long oldSeniors) {
        this.oldSeniors = oldSeniors;
    }

}
