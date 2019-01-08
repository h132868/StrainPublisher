package com.sematek.StrainGauge.Publisher;

import com.fazecast.jSerialComm.*;
import org.eclipse.paho.client.mqttv3.MqttException;
import java.net.URISyntaxException;
import java.time.Instant;
import static java.lang.Thread.sleep;

public class Listener implements Runnable{

    private SerialPort comPort;
    private Publisher publisher;
    private int sensorId;
    private String payload;
    private Instant timestamp;



    Listener(int comPortNumber) throws URISyntaxException, MqttException {

        comPort= SerialPort.getCommPort(new String("COM"+comPortNumber));
        sensorId = comPortNumber;
        publisher = new Publisher(sensorId);
    }

    public void run() {
        System.out.println("Listener running on port " + sensorId + "...");
        comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
            @Override
            public void serialEvent(SerialPortEvent event)
            {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(newData, newData.length);
                System.out.println("Read " + numRead + " bytes.");
            }
        });

        if (comPort.bytesAvailable()>1) {
            byte[] buffer = new byte[1024];
            payload = String.valueOf(comPort.readBytes(buffer, comPort.bytesAvailable()));
            timestamp = Instant.EPOCH;
            try {
                publisher.publish(payload, timestamp.toString());
            } catch (MqttException e) {
                e.printStackTrace();
            }

        }
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
