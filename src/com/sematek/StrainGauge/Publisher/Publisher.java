package com.sematek.StrainGauge.Publisher;

import Util.LoginUtil;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.URI;
import java.net.URISyntaxException;

public class Publisher implements MqttCallback {

    private final int qos = 1;
    private String topic;
    private int sensorNumber;
    private MqttClient client;

    Publisher(int sensorNumber) throws MqttException, URISyntaxException {
    this.sensorNumber = sensorNumber;
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
    void disconnect() throws MqttException {
        client.disconnect();
        client.close();
    }

    public void publish(String readData, String timestamp) throws MqttException{
        MqttMessage message = new MqttMessage();
        message.setRetained(true);
        message.setQos(qos);
        String payloadStr = ("{{\"sensordata\":{\"data\": \"" + readData + "\", " + "\"unixTimestamp\": \"" + timestamp +"\"}}");
        message.setPayload(payloadStr.getBytes());
        client.publish(topic,message);
    }

    private String[] getAuth(URI uri) {
        String a = uri.getAuthority();
        String[] first = a.split("@");
        return first[0].split(":");
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

        System.out.println("Message arrived!");
    }
}

