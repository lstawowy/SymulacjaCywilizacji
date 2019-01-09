package com.spring.simulation.greek.map_generator;

import com.spring.simulation.greek.enums.MapType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapGenerator {

  public static void main(String[] args) {
    Map map = new Map();

    map.readMap(MapReader.readResource("maps/mountains.jpg"), MapType.MOUNTAIN_MAP);
    map.readMap(MapReader.readResource("maps/rivers.jpg"),MapType.RIVER_MAP);
    map.findLandBorder();
    map.setDistancesToWater();
    map.drawMap();
  }
}
