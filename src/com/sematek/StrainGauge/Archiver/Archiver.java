package com.sematek.StrainGauge.Archiver;

/*
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.mongodb.client.*;
*/

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCommandException;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import com.mongodb.util.JSON;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

//DB login: inception / c3PqzMaEN9BrH9NB

public class Archiver {
    MongoClientURI uri;
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection coll;

    public Archiver() {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://inception:OPGtoJ3DfRbL9RX9@cluster0-puaeg.mongodb.net/test?retryWrites=true");
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("stjernelaks");
        coll = db.getCollection("sensor");
    }


    public void archiveData (String jsonString) {
        Document doc = Document.parse(jsonString);
        coll.insertOne(doc);
    };

    public void ensureTopicExistsInDB(String topic, String desc) {
        coll.updateOne(eq("topic", topic), new Document("$set", new Document("topic", topic).append("desc", desc))
        );

    }
}
