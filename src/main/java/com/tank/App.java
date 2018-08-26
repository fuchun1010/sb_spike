package com.tank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author fuchun
 */
@SpringBootApplication
@ServletComponentScan
public class App {
  public static void main(final String... args) {
    SpringApplication.run(App.class, args);
  }
}
