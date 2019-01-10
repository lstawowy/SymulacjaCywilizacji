package com.spring.simulation.greek.resource;

import com.spring.simulation.greek.Simulation.Simulation;
import com.spring.simulation.greek.map_generator.MapGenerator;
import com.spring.simulation.greek.map_generator.MapReader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/map")
public class ResourcesResource {

  @GetMapping(value = "/rivers", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getRiversMap() {
    return MapReader.readResourceAsByteArray("maps/rivers.png");
  }

  @GetMapping(value = "/mountains", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getMountainsMap() {
    return MapReader.readResourceAsByteArray("maps/mountains.png");
  }

  @GetMapping(value = "/rivers_only", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getRiversOnlyMap() {
    return MapReader.readResourceAsByteArray("maps/mapa_rzek.png");
  }

  @GetMapping(value = "/landform", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getLandformMap() {
    return MapReader.readResourceAsByteArray("maps/mapa_uksztaltowanie.png");
  }

  @GetMapping(value = "/resources", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getResourcesMap() {
    Simulation.main(null);
    return MapReader.readResourceAsByteArray("newmap.gif");
  }

}


