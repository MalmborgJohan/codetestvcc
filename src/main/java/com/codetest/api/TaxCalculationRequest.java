package com.codetest.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaxCalculationRequest {

    private String vehicle;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Europe/Stockholm")
    private Date[] dates;

    public String getVehicle() {
        return vehicle;
    }

    public TaxCalculationRequest setVehicle(String vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public Date[] getDates() {
        return dates;
    }

    public TaxCalculationRequest setDates(Date[] dates) {
        this.dates = dates;
        return this;
    }
}
