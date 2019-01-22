package com.spring.simulation.greek.resource;

import com.spring.simulation.greek.simulation.Simulation;
import com.spring.simulation.greek.map_generator.MapReader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/map")
public class ResourcesResource {

  private static final String MAPS_PATH = "maps/";
  private static final String NATURAL_RESOURCES_PATH = MAPS_PATH + "natural_resources_maps/";

  @GetMapping(value = "/resources", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getResourcesMap() {
    Simulation.main(null);
    return MapReader.readResourceAsByteArray(MAPS_PATH + "calosc.gif");
  }

  @GetMapping(value = "/rivers", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getRiversMap() {
    return MapReader.readResourceAsByteArray(MAPS_PATH + "rivers.png");
  }

  @GetMapping(value = "/mountains", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getMountainsMap() {
    return MapReader.readResourceAsByteArray(MAPS_PATH + "mountains.png");
  }

  @GetMapping(value = "/rivers_only", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getRiversOnlyMap() {
    return MapReader.readResourceAsByteArray(MAPS_PATH + "mapa_rzek.gif");
  }

  @GetMapping(value = "/landform", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getLandformMap() {
    return MapReader.readResourceAsByteArray(
        MAPS_PATH + "mapa_uksztaltowanie.gif");
  }

  @GetMapping(value = "/climate", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getClimateMap() {
    return MapReader.readResourceAsByteArray(MAPS_PATH + "mapa_klimatu.gif");
  }

  @GetMapping(value = "/coal", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getCoalMap() {
    return MapReader.readResourceAsByteArray(NATURAL_RESOURCES_PATH + "coal.png");
  }

  @GetMapping(value = "/copper", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getCopperMap() {
    return MapReader.readResourceAsByteArray(NATURAL_RESOURCES_PATH+ "copper.png");
  }

  @GetMapping(value = "/iron", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getIronMap() {
    return MapReader.readResourceAsByteArray(NATURAL_RESOURCES_PATH+ "iron.png");
  }

  @GetMapping(value = "/lead", produces = MediaType.IMAGE_PNG_VALUE)
  public @ResponseBody
  byte[] getLeadMap() {
    return MapReader.readResourceAsByteArray(NATURAL_RESOURCES_PATH+ "lead.png");
  }

}


