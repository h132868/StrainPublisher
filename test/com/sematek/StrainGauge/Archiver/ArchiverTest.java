package com.sematek.StrainGauge.Archiver;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ArchiverTest {

    @Test
    void archiveData() throws MqttException, URISyntaxException {
        Archiver archiver = new Archiver(0);

        String payloadStr = ("{data: " + "300.00" + ", " + "epoch: " + "123234456789" +"}");

        archiver.archiveData(payloadStr);

    }
}