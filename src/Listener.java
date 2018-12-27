import com.fazecast.jSerialComm.*;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.net.URISyntaxException;

public class Listener implements Runnable{

    SerialPort comPort;
    Publisher publisher;
    int sensorId;
    String payload;



    Listener(int comPortNumber) throws URISyntaxException, MqttException {

        comPort= SerialPort.getCommPort(new String("COM"+comPortNumber));
        sensorId = comPortNumber;
        Publisher publisher = new Publisher(sensorId);
    }

    public void run() {
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        try {
            while (true)
            {
                byte[] readBuffer = new byte[1024];
                payload = readBuffer.toString();
                publisher.publish(payload);
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        } catch (Exception e) { e.printStackTrace(); }
        comPort.closePort();
    }
    public int getSensorId() {
        return sensorId;
    }

}
