package com.spring.simulation.greek.resource;

import com.spring.simulation.greek.simulation.Simulation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/map")
public class RunResource {

  @GetMapping(value = "/run")
  public void runGenerator() {
    Simulation.main(null);
  }
}


