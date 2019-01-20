package com.spring.simulation.greek.map_generator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

@Slf4j
public class MapReader {
  private static final ClassLoader CLASS_LOADER = MapReader.class.getClassLoader();
  private static final String RESOURCES_PATH = "src/main/resources/";
  private static final String WRITE_FILE_FORMAT = "gif";
  private static final String IOEXCEPTION_ERROR_MESSAGE = "Envountered IOException :";

  static BufferedImage readResource(String path) {
    BufferedImage resource = null;
    try {
      resource = ImageIO
          .read(Objects.requireNonNull(CLASS_LOADER.getResourceAsStream(path)));
    } catch (IOException e) {
      log.error(IOEXCEPTION_ERROR_MESSAGE, e);
    }
    return resource;
  }

  static void saveImageInResources(BufferedImage bufferedImage, int d) {
    try {
      String filename = "newmap"+d+".gif";
      File outputfile = new File(RESOURCES_PATH + filename);
      ImageIO.write(bufferedImage, WRITE_FILE_FORMAT, outputfile);
    } catch (IOException e) {
      log.error(IOEXCEPTION_ERROR_MESSAGE, e);
    }
  }

  public static byte[] readResourceAsByteArray(String path) {
    byte[] byteArray = null;
    try {
      InputStream resource = Objects.requireNonNull(CLASS_LOADER.getResourceAsStream(path));
      byteArray = IOUtils.toByteArray(Objects.requireNonNull(resource));
    } catch (IOException e) {
      log.error(IOEXCEPTION_ERROR_MESSAGE, e);
    }
    return byteArray;
  }

}
