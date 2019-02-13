package com.sematek.StrainGauge.Publisher;

import  org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class PublisherTest {

    @BeforeEach
    void setUp() {


    }

    @Test
    void publish() throws MqttException, URISyntaxException {
        String payload = "555.99";
        String dateTimeEpoch = "1547720904";
        Publisher p = new Publisher(0);
        p.connect();
        p.publish(payload,dateTimeEpoch);
        p.disconnect();

    }

}