package com.sematek.StrainGauge.controller;
        import Util.LoginUtil;
        import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
        import org.eclipse.paho.client.mqttv3.MqttCallback;
        import org.eclipse.paho.client.mqttv3.MqttClient;
        import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
        import org.eclipse.paho.client.mqttv3.MqttException;
        import org.eclipse.paho.client.mqttv3.MqttMessage;
        import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

        import java.net.URI;
        import java.net.URISyntaxException;

class Subscriber implements MqttCallback, Runnable {

    private String message;
    private String topic;
    private int sensorId;
    private Controller controller;
    private MqttClient client;
    private int qos;


    public Subscriber(int sensorId) {
        this.topic = LoginUtil.getTopic(sensorId);
        this.sensorId = sensorId;
        qos = 1;
    }

    public void run() {
            connect();
    }
    void connect() {


        try {
            URI uri = LoginUtil.getURI(sensorId);
            this.topic = LoginUtil.getTopic(sensorId);
            String host = String.format("tcp://%s:%d", uri.getHost(), uri.getPort());

            this.client = new MqttClient(host, LoginUtil.getClientId(), new MemoryPersistence());
            MqttConnectOptions conOpt = new MqttConnectOptions();
            conOpt.setCleanSession(true);
            conOpt.setUserName(LoginUtil.getUsername(sensorId));
            conOpt.setPassword( LoginUtil.getPassword(sensorId).toCharArray());
            System.out.println("Connecting to broker...");
            this.client.setCallback(this);
            this.client.connect(conOpt);
            System.out.println("Connected");
            client.subscribe(topic, qos);
            System.out.println("Subscribed to message with topic " + topic + "...");

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

    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);

    }

    /**
     * @see MqttCallback#messageArrived(String, MqttMessage)
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        String dismessage = String.format("[%s] %s", topic, new String(message.getPayload()));

        this.setMessage(new String(message.getPayload()));
        controller.exportLastValToWeb(getMessage());
    }

    /**
     * @see MqttCallback#deliveryComplete(IMqttDeliveryToken)
     */
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub

    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

}