package com.spring.simulation.greek.entity;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@lombok.Data
public class Map {

  byte[] map;


  public static BufferedImage getBufferedImageFromMap(Map mapToRead) throws IOException {
    return ImageIO.read(new ByteArrayInputStream(mapToRead.map));
  }

  public static BufferedImage readMapFromFile(String pathToMap) throws IOException {
    return ImageIO.read(new File(pathToMap));
  }

  public static void saveMap(BufferedImage mapToSave) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(mapToSave, "png", outputStream);
  }

}
