package com.spring.simulation.greek.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/map")
public class MapResource {

  @GetMapping(value = "/current", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] getCurrentMap() throws IOException {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("current_map.jpg");
    return IOUtils.toByteArray(Objects.requireNonNull(in));
  }

  @GetMapping(value = "/resources", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getResourcesMap() throws IOException {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream("resources_map.png");
    return IOUtils.toByteArray(Objects.requireNonNull(in));
  }

}


