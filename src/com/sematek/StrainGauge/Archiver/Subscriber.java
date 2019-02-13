package com.sematek.StrainGauge.Archiver;

import Util.LoginUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.URI;
import java.net.URISyntaxException;


public class Subscriber implements MqttCallback, Runnable {

    private final int sensorNumber;
    Archiver archiver;

    private final int qos = 1;
    private String topic;
    private MqttClient client;

    Subscriber(int sensorNumber) throws MqttException, URISyntaxException {
        this.sensorNumber = sensorNumber;
        archiver = new Archiver();
        archiver.ensureTopicExistsInDB(LoginUtil.getTopic(sensorNumber),LoginUtil.getTopicDescription(sensorNumber));
    }


    void connect() {


        try {
            URI uri = LoginUtil.getURI();
            this.topic = LoginUtil.getTopic(sensorNumber);
            String host = String.format("tcp://%s:%d", uri.getHost(), uri.getPort());

            this.client = new MqttClient(host, LoginUtil.getClientId(), new MemoryPersistence());
            MqttConnectOptions conOpt = new MqttConnectOptions();
            conOpt.setCleanSession(true);
            conOpt.setUserName(LoginUtil.getUsername());
            conOpt.setPassword( LoginUtil.getPassword().toCharArray());
            System.out.println("Connecting to broker...");
            this.client.setCallback(this);
            this.client.connect(conOpt);
            System.out.println("Connected");

        } catch (MqttException e) {
            System.out.println("reason " + e.getReasonCode());
            System.out.println("msg " + e.getMessage());
            System.out.println("loc " + e.getLocalizedMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("excep " + e);
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private void disconnect() throws MqttException {
        client.disconnect();
    }

    public void publish(String payload) throws MqttException{
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        client.publish(LoginUtil.getTopic(sensorNumber),message);
    }


    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);
    }

    /**
     * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
     */
    public void deliveryComplete(IMqttDeliveryToken token) {
    }
    /**
     * @see MqttCallback#messageArrived(String, MqttMessage)
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        System.out.println("Archiver message arrived!");
        archiver.archiveData(message.toString());

    }

    @Override
    public void run() {

    }
}

