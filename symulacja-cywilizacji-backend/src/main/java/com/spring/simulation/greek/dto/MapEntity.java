package com.spring.simulation.greek.dto;

@lombok.Data
public class MapEntity {

  private byte[] byteMap;

  private int iteration;

  public MapEntity(byte[] byteMap, int iteration) {
    this.byteMap = byteMap;
    this.iteration = iteration;
  }
}
