package Util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class LoginUtil {

    private static List<String> username = List.of("vjkulmgc","rnbptasw","pssweqsv","pcyhumbk");
    private static List<String> password = List.of("FGJ6y0KrhOdG","bV5OuLpEVR_E","1wGQaOywB2VT","xWP7Jbwp9Rts");
    private static List<String> port = List.of("15561","19327","18649","19596");
    //topic prefix
    private static final String tP = "Sematek.Stjernelaks.StrainGauge.Sensor";
    private static List<String> topic = List.of(tP+"0",tP+"1",tP+"2",tP+"3");
    private static final String clientId = "StrainGauge";
    private static final String serverUrl = "m20.cloudmqtt.com";
    private static List<String> desc = List.of("description 1", "description 2", "description 3", "description 4", "description 5", "description 6", "description 7");


    public static String getClientId() {
        return clientId;
    }

    public static String getUsername(int i) {
        return username.get(i);
    }

    public static String getPassword(int i) {
        return password.get(i);
    }

    public static String getPort(int i) {
        return port.get(i);
    }
    public static String getServerUrl() {
        return serverUrl;
    }
    public static URI getURI(int i) throws URISyntaxException {
        return new URI("tcp://" + getServerUrl() + ":" + getPort(i));
    }

    public static String getTopicDescription(int i) { return desc.get(i);}
    public static String getTopic(int i) {
        return topic.get(i);
    }


    public static String getUsername() { return username.get(0); }
    public static String getPassword() {
        return password.get(0);
    }
    public static String getPort() {
        return port.get(0);
    }
    public static String getTopic() {
        return topic.get(0);
    }
    public static String getTopicPrefix() {
        return tP;
    }
    public static URI getURI() throws URISyntaxException {
        return new URI("tcp://" + getServerUrl() + ":" + getPort(0));
    }
}
