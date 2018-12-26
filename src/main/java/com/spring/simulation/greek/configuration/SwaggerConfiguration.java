package com.spring.simulation.greek.configuration;

import static springfox.documentation.builders.PathSelectors.any;

import com.google.common.base.Predicate;
import java.util.Collections;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = "com.spring.simulation.greek")
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(getSwaggerPaths())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Civilisation Simulation",
        "Simulation of civilisation",
        "0.0.1-SNAPSHOT",
        "",
        new Contact("Łukasz", "", "l.w.stawowy@gmail.com"),
        "Apache 2.0",
        "http://www.apache.org/licenses/LICENSE-2.0.html",
        Collections.singletonList(createNewVendorExtension(null, null))
    );
  }

  private VendorExtension createNewVendorExtension(String name, String value) {
    return new VendorExtension() {
      @Override
      public String getName() {
        return name;
      }

      @Override
      public Object getValue() {
        return value;
      }
    };
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  private Predicate<String> getSwaggerPaths() {
    return any();
  }
}