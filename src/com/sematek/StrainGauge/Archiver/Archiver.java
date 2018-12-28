package com.sematek.StrainGauge.Archiver;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

//DB login: inception / c3PqzMaEN9BrH9NB

public class Archiver {
    MongoClientURI uri = new MongoClientURI("mongodb+srv://inception:<PASSWORD>@cluster0-puaeg.mongodb.net/test?retryWrites=true");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("test");
}
