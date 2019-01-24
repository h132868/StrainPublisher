package com.sematek.StrainGauge.controller;

public class SensorMessage {

    private double data;
    private long epoch;
    private double temp;

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
