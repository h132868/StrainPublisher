package com.sematek.StrainGauge.Archiver;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import javafx.scene.shape.Arc;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.net.URISyntaxException;

//DB login: inception / c3PqzMaEN9BrH9NB

public class Archiver {
    MongoClientURI uri;
    MongoClient mongoClient;
    DB database;
    Subscriber subscriber;
    DBCollection collection;

    public Archiver(int sensorId) throws MqttException, URISyntaxException {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://inception:OPGtoJ3DfRbL9RX9@cluster0-puaeg.mongodb.net/test?retryWrites=true");
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDB("test");
        subscriber = new Subscriber(sensorId);
        collection = database.getCollection("sensor" + sensorId);

    }

    public void archiveData (String jsonString) {
        collection.insert((DBObject) JSON.parse(jsonString));
    };



}
