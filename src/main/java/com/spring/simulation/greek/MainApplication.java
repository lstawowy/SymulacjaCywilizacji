package com.spring.simulation.greek;

import com.spring.simulation.greek.configuration.SwaggerConfiguration;
import com.spring.simulation.greek.repository.TerrainRepository;
import com.spring.simulation.greek.resource.TerrainResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = {SwaggerConfiguration.class, TerrainResource.class})
public class MainApplication {

  @Autowired
  private TerrainRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

}
