package com.sematek.StrainGauge.Archiver;

import Util.LoginUtil;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static Util.LoginUtil.getTopic;
import static Util.LoginUtil.getTopicDescription;
import static org.junit.jupiter.api.Assertions.*;

class ArchiverTest {

    @Test
    void archiveData() throws MqttException, URISyntaxException {

        Subscriber subscriber = new Subscriber(0);
        subscriber.connect();
        subscriber.run();
        String payloadStr = ("{\"sensordata\":{\"data\": \"500\", \"unixTimestamp\": \"12345678\"}}");
        subscriber.archiver.archiveData(payloadStr);

    }
}