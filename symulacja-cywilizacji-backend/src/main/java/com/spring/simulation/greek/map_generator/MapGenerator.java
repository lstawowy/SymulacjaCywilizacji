package com.spring.simulation.greek.map_generator;

import com.spring.simulation.greek.enums.MapType;
import com.spring.simulation.greek.enums.ResourceType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapGenerator {

  public static Map readDataFromMapImages() {
    Map map = new Map();

    map.readMap(MapReader.readResource("maps/mountains.png"), MapType.MOUNTAIN_MAP);
    map.readMap(MapReader.readResource("maps/rivers.png"),MapType.RIVER_MAP);
    map.readMap(MapReader.readResource("maps/klimat.png"),MapType.CLIMATE_MAP);
    map.readNaturalResourcesMap(MapReader.readResource("maps/natural_resources_maps/iron.png"), ResourceType.IRON);
    map.readNaturalResourcesMap(MapReader.readResource("maps/natural_resources_maps/copper.png"), ResourceType.COPPER);
    map.readNaturalResourcesMap(MapReader.readResource("maps/natural_resources_maps/coal.png"), ResourceType.COAL);
    map.readNaturalResourcesMap(MapReader.readResource("maps/natural_resources_maps/lead.png"), ResourceType.LEAD);
    map.findLandBorder();
    map.setDistancesToWater();

    return map;
  }
}
