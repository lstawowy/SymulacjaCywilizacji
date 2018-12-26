package com.spring.simulation.greek.entity;

import com.spring.simulation.greek.dto.CoordinatesDto;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@lombok.Data
public class TerrainEntity {

  @Id
  private ObjectId id;

  private CoordinatesDto coordinates;

  public TerrainEntity() {
  }

  public TerrainEntity(CoordinatesDto coordinates) {
    this.coordinates = coordinates;
  }

}