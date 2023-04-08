package com.example.carbontouch;

public class Data {
    private double carbonIntensity;
    private double fossilFuelPercentage;
    private double nuclearPercentage;
    private double renewablePercentage;
    private String timestamp;

    // Getters and setters
    public double getCarbonIntensity() {
        return carbonIntensity;
    }

    public void setCarbonIntensity(double carbonIntensity) {
        this.carbonIntensity = carbonIntensity;
    }

    public double getFossilFuelPercentage() {
        return fossilFuelPercentage;
    }

    public void setFossilFuelPercentage(double fossilFuelPercentage) {
        this.fossilFuelPercentage = fossilFuelPercentage;
    }

    public double getNuclearPercentage() {
        return nuclearPercentage;
    }

    public void setNuclearPercentage(double nuclearPercentage) {
        this.nuclearPercentage = nuclearPercentage;
    }

    public double getRenewablePercentage() {
        return renewablePercentage;
    }

    public void setRenewablePercentage(double renewablePercentage) {
        this.renewablePercentage = renewablePercentage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
