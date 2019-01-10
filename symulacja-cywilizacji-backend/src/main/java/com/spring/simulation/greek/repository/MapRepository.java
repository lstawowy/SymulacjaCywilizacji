//package com.spring.simulation.greek.repository;
//
//import com.spring.simulation.greek.map_generator.Map;
//import java.util.List;
//import org.bson.types.ObjectId;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//public interface MapRepository extends MongoRepository<Map, String> {
//
//  Map findById(ObjectId id);
//
//  List<Map> findAll();
//}