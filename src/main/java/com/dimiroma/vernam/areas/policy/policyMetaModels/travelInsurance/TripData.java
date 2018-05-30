package com.dimiroma.vernam.areas.policy.policyMetaModels.travelInsurance;

import java.io.Serializable;

public class TripData implements Serializable {
    private String from;
    private String to;

    private String period;
    private String startDate;

    public TripData() {
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
