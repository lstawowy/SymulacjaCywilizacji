package com.spring.simulation.greek.resource;

import com.spring.simulation.greek.map_generator.MapReader;
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
public class CivilisationsResource {

  @GetMapping(value = "/current", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] getCurrentMap() {
    return MapReader.readResourceAsByteArray("current_map.jpg");
  }


}


