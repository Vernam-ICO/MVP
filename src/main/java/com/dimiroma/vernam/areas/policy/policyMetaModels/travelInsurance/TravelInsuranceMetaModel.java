package com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance;

import com.dimiroma.vernam.areas.policy.policyMetaModels.PolicyMetaModel;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.enums.GroupType;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.enums.PurposeType;
import com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance.enums.TripType;

public class TravelInsuranceMetaModel extends PolicyMetaModel {
    private TripType tripType;
    private TripData tripData;

    private PurposeType purpose;
    private String destination;

    private GroupType groupType;
    private GroupData groupData;

    private boolean changeOfStayExpenses;
    private boolean legalAidAbroad;
    private boolean personalResponsibility;
    private boolean reliableWallet;

    public TravelInsuranceMetaModel() {

    }

    public TripType getTripType() {
        return this.tripType;
    }

    public void setTripType(final TripType tripType) {
        this.tripType = tripType;
    }

    public TripData getTripData() {
        return this.tripData;
    }

    public void setTripData(final TripData tripData) {
        this.tripData = tripData;
    }

    public PurposeType getPurpose() {
        return this.purpose;
    }

    public void setPurpose(final PurposeType purpose) {
        this.purpose = purpose;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public GroupType getGroupType() {
        return this.groupType;
    }

    public void setGroupType(final GroupType groupType) {
        this.groupType = groupType;
    }

    public GroupData getGroupData() {
        return this.groupData;
    }

    public void setGroupData(final GroupData groupData) {
        this.groupData = groupData;
    }

    public boolean isChangeOfStayExpenses() {
        return this.changeOfStayExpenses;
    }

    public void setChangeOfStayExpenses(final boolean changeOfStayExpenses) {
        this.changeOfStayExpenses = changeOfStayExpenses;
    }

    public boolean isLegalAidAbroad() {
        return this.legalAidAbroad;
    }

    public void setLegalAidAbroad(final boolean legalAidAbroad) {
        this.legalAidAbroad = legalAidAbroad;
    }

    public boolean isPersonalResponsibility() {
        return this.personalResponsibility;
    }

    public void setPersonalResponsibility(final boolean personalResponsibility) {
        this.personalResponsibility = personalResponsibility;
    }

    public boolean isReliableWallet() {
        return this.reliableWallet;
    }

    public void setReliableWallet(final boolean reliableWallet) {
        this.reliableWallet = reliableWallet;
    }
}
