import com.oracle.tools.packager.Log;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.URI;
import java.net.URISyntaxException;

public class Publisher implements MqttCallback {

    private Listener listener;
    private final int sensorId;

    private final int qos = 1;
    private String topic;
    private MqttClient client;

    Publisher(int sensorId) throws MqttException, URISyntaxException {
        this.sensorId = sensorId;
        URI uri = LoginDetails.getURI(sensorId);
        this.topic = LoginDetails.getTopic(sensorId);
        String host = String.format("tcp://%s:%d", uri.getHost(), uri.getPort());


        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        conOpt.setUserName(LoginDetails.getUsername(sensorId));
        conOpt.setPassword( LoginDetails.getPassword(sensorId).toCharArray());

        this.client = new MqttClient(host, LoginDetails.getClientId(), new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect(conOpt);
    }

    public void publish(String payload) throws MqttException{
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(qos);
        client.publish(LoginDetails.getTopic(sensorId),message);
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
    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
    }

}

