package com.sematek.StrainGauge.Publisher;

import org.eclipse.paho.client.mqttv3.MqttException;

import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws MqttException, URISyntaxException, InterruptedException {
        Listener l1 = new Listener(0,0);
        /*Listener l2 = new Listener(1);
        Listener l3 = new Listener(2);
        Listener l4 = new Listener(3);*/

        Thread t1 = new Thread(l1);
       /* Thread t2 = new Thread(l2);
        Thread t3 = new Thread(l3);
        Thread t4 = new Thread(l4);*/

        t1.start();
       /* t2.start();
        t3.start();
        t4.start();*/

        t1.join();
       /* t2.join();
        t3.join();
        t4.join();*/


    }



}
