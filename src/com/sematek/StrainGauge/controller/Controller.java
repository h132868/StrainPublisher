package com.sematek.StrainGauge.controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Gets live data for use by the web part. Lookup of content from MongoDB from a different place.
public class Controller {

    private int sensorId;
    private double sensorVal;

    public Controller(int sensorId) {
        this.sensorId = sensorId;
    }


    public double parseInput(String str) {
        String regex = "[0-9]+.[0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return Double.parseDouble(m.group());
        } else {
            return 0.0;
        }
    }

    //TODO:
    public void exportLastValToWeb(String message) {
        sensorVal = parseInput(message);
    }
}
