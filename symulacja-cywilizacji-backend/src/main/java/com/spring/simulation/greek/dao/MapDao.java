package com.spring.simulation.greek.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.spring.simulation.greek.dto.MapEntity;

public class MapDao {

  private MongoClient mongoClient;
  private DB db;
  private DBCollection mapsCollection;

  public MapDao() {
    mongoClient = new MongoClient();
    db = mongoClient.getDB("map");
    mapsCollection = db.getCollection("maps");
  }

  public void save(MapEntity entity){
    BasicDBObject document = new BasicDBObject();
    document.put("map", entity.getByteMap());
    document.put("iteration", entity.getIteration());
    mapsCollection.insert(document);
  }


}
