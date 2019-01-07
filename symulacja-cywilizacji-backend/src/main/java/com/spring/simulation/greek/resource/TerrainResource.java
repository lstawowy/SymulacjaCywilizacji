package com.spring.simulation.greek.resource;

import com.spring.simulation.greek.entity.TerrainEntity;
import com.spring.simulation.greek.repository.TerrainRepository;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/terrain")
public class TerrainResource {

  @Autowired
  private TerrainRepository repository;


  @GetMapping(value = "/")
  public List<TerrainEntity> getAllTerrains() {
    return repository.findAll();
  }

  @GetMapping(value = "/{id}")
  public TerrainEntity getTerrainById(@PathVariable("id") ObjectId id) {
    return repository.findById(id);
  }

}


