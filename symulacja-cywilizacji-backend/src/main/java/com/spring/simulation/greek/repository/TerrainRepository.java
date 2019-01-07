package com.spring.simulation.greek.repository;

import com.spring.simulation.greek.entity.TerrainEntity;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TerrainRepository extends MongoRepository<TerrainEntity, String> {

  TerrainEntity findById(ObjectId id);

  List<TerrainEntity> findAll();
}