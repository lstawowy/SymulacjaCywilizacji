package com.spring.simulation.greek.resource;

import com.spring.simulation.greek.Simulation.Simulation;
import com.spring.simulation.greek.map_generator.MapGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/run")
public class RunResource {

  @PostMapping(value = "/map_generator")
  public void getAllTerrains() {
    Simulation.main(null);
  }
}


