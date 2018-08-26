package com.tank.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fuchun
 */
@Configuration
@Slf4j
public class PrePareData {

  @Bean
  public CommandLineRunner prepareDateWhenStart() {
    return args -> {
      System.out.println("......prepare data when server start.....");
      log.info(String.format("prepare data %s", "start"));
    };
  }
}
