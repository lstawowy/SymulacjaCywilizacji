package com.spring.simulation.greek.map_generator;

import com.spring.simulation.greek.dao.MapDao;
import com.spring.simulation.greek.dto.MapEntity;
import com.spring.simulation.greek.repository.MapRepository;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MapReader {

  private static final ClassLoader CLASS_LOADER = MapReader.class.getClassLoader();
  private static final String RESOURCES_PATH = "src/main/resources/";
  private static final String WRITE_FILE_FORMAT = "gif";
  private static final String IOEXCEPTION_ERROR_MESSAGE = "Envountered IOException :";
  private static MapDao dao;

  public MapReader() {
    dao = new MapDao();
  }

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
      String filename = "newmap" + d + ".gif";
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

  public void saveImageInDatabase(BufferedImage map, int d) throws IOException {
    byte[] byteMap = convertBufferedImageToByteArray(map);
    MapEntity entity = new MapEntity(byteMap, d);
    dao.save(entity);
  }

  private static byte[] convertBufferedImageToByteArray(BufferedImage map) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(map, "jpg", baos);
    baos.flush();
    byte[] imageInByte = baos.toByteArray();
    baos.close();
    return imageInByte;
  }

}
