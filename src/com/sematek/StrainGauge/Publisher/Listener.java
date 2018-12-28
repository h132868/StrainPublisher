package com.sematek.StrainGauge.Publisher;

import com.fazecast.jSerialComm.*;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.net.URISyntaxException;

import static java.lang.Thread.sleep;

public class Listener implements Runnable{

    SerialPort comPort;
    Publisher publisher;
    int sensorId;
    String payload;
    String newPayload;



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
                newPayload = readBuffer.toString();
                if (!newPayload.equals(payload)) {
                    publisher.publish(payload);
                }
                payload = newPayload;
                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        } catch (Exception e) { e.printStackTrace(); }
        comPort.closePort();
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int getSensorId() {
        return sensorId;
    }

}
