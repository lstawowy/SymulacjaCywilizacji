package com.spring.simulation.greek;

import com.spring.simulation.greek.configuration.SwaggerConfiguration;
//import com.spring.simulation.greek.repository.MapRepository;
import com.spring.simulation.greek.repository.MapRepository;
import com.spring.simulation.greek.resource.RunResource;
import com.spring.simulation.greek.simulation.Simulation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {SwaggerConfiguration.class})
public class MainApplication {

  @Autowired
  private MapRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

}
