package com.sematek.StrainGauge.controller;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// Gets live data for use by the web part. Lookup of content from MongoDB from a different place.
public class Controller {

    private int sensorId;
    private double sensorVal;

    public Controller(int sensorId) {
        this.sensorId = sensorId;
    }


    public SensorMessage parseInput(String jsonInString) {
        ObjectMapper mapper = new ObjectMapper();
        SensorMessage sensorMessage = null;
        try {
            // Convert JSON string to Object
            sensorMessage = mapper.readValue(jsonInString, SensorMessage.class);
            System.out.println(sensorMessage);

            //Pretty print
            String prettySensorMessage = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sensorMessage);
            System.out.println(prettySensorMessage);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sensorMessage;

    /*
        String regex = "[0-9]+.[0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return Double.parseDouble(m.group());
        } else {
            return 0.0;
        }
        */
    }

    //TODO:
    public void exportLastValToWeb(String message) {
        System.out.println(message);
        System.out.println(parseInput(message));
    }
}
