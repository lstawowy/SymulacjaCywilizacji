package com.spring.simulation.greek.resource;

import com.spring.simulation.greek.map_generator.MapReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/map")
public class CivilisationsResource {

  @GetMapping(value = "/current/{iteration}", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] getCurrentMap(@PathVariable("iteration") int iteration) {
    return MapReader.readResourceAsByteArray("newmap"+iteration+".gif");
  }

  @GetMapping(value = "/bc_600", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] getMap600BC() {
    return MapReader.readResourceAsByteArray("maps/adm_maps/600BC.png");
  }

  @GetMapping(value = "/bc_650", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] getMap650BC() {
    return MapReader.readResourceAsByteArray("maps/adm_maps/650BC.png");
  }

  @GetMapping(value = "/bc_700", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] getMap700BC() {
    return MapReader.readResourceAsByteArray("maps/adm_maps/700BC.png");
  }

  @GetMapping(value = "/bc_750", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] getMap750BC() {
    return MapReader.readResourceAsByteArray("maps/adm_maps/750BC.png");
  }

  @GetMapping(value = "/bc_800", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody
  byte[] getMap800BC() {
    return MapReader.readResourceAsByteArray("maps/adm_maps/800BC.png");
  }


}


