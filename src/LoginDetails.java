import java.net.URI;
import java.net.URISyntaxException;

public class LoginDetails {

    private static String[] username = new String[4];
    private static final String[] password = new String[4];
    private static final String[] port = new String[4];



    private static final String[] topic = new String[4];
    private static final String serverUrl = "m20.cloudmqtt.com";

    LoginDetails() {
        username[0] = "vjkulmgc";
        username[1] = "rnbptasw";
        username[2] = "pssweqsv";
        username[3] = "pcyhumbk";

        password[0] = "FGJ6y0KrhOdG";
        password[1] = "bV5OuLpEVR_E";
        password[2] = "1wGQaOywB2VT";
        password[3] = "xWP7Jbwp9Rts";

        port[0] = "15561";
        port[1] = "19327";
        port[2] = "18649";
        port[3] = "19596";

        topic[0] = "Sematek.Stjernelaks.StrainGauge0";
        topic[1] = "Sematek.Stjernelaks.StrainGauge1";
        topic[2] = "Sematek.Stjernelaks.StrainGauge2";
        topic[3] = "Sematek.Stjernelaks.StrainGauge3";

    }

    public static String getClientId() {
        return clientId;
    }

    private static final String clientId = "StrainGauge";

    public static String getUsername(int i) {
        return username[i];
    }

    public static String getPassword(int i) {
        return password[i];
    }

    public static String getPort(int i) {
        return port[i];
    }
    public static String getTopic(int i) {
        return topic[i];
    }
    public static String getServerUrl() {
        return serverUrl;
    }
    public static URI getURI(int i) throws URISyntaxException {
        return new URI("tcp://" + getServerUrl() + ":" + getPort(i));
    }

}
