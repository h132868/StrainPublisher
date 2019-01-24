package com.sematek.StrainGauge.controller;

import org.eclipse.paho.client.mqttv3.MqttException;

public class Main {

    public static void main(String[] args) throws MqttException {
        Subscriber s = new Subscriber(0);
        Thread t = new Thread(s);
        t.start();

    }
}
