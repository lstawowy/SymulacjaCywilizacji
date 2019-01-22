package com.spring.simulation.greek.repository;

import com.spring.simulation.greek.dto.MapEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface MapRepository extends MongoRepository<MapEntity, String> {

  List<MapEntity> findAll();

  MapEntity save(MapEntity entity);

  MapEntity findByIteration(int iteration);
}