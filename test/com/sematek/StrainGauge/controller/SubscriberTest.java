package com.sematek.StrainGauge.controller;

import Util.LoginUtil;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberTest {

    Subscriber s = new Subscriber(0);



/*
    @Test
    void messageArrived() throws Exception {
        String topic0 = LoginUtil.getTopic(0);
        MqttMessage message = new MqttMessage();
        message.setQos(1);
        String payload = "999.99";
        String dateTimeEpoch = "23153663";
        String payloadStr = ("{data: " + payload + ", " + "epoch: " + dateTimeEpoch +"}");
        message.setPayload(payloadStr.getBytes());

        Assertions.assertEquals(message,s.messageArrived(topic0,message));
    }
    */
}