package com.spring.simulation.greek.map_generator;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapGenerator {

  private static final ClassLoader CLASS_LOADER = MapGenerator.class.getClassLoader();

  public static void main(String[] args) {
    Map map = new Map();
    BufferedImage mountain_map = null;
    BufferedImage river_map = null;
    try {
      mountain_map = ImageIO
          .read(Objects.requireNonNull(CLASS_LOADER.getResourceAsStream("maps/mountains.jpg")));
      river_map = ImageIO.read(
          Objects.requireNonNull(CLASS_LOADER.getResourceAsStream("maps/rivers.jpg")));
    } catch (IOException e) {
      log.error(e.getMessage());
    }

    map.readMap(mountain_map, Map.MapType.MOUNTAIN_MAP);
    map.readMap(river_map, Map.MapType.RIVER_MAP);
    map.findLandBorder();
    map.setDistancesToWater();
    map.drawMap();
  }
}
