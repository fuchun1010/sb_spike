package com.tank.config;

import lombok.val;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpTransportConfig {

  @Bean
  public RestTemplate initRestTemplate(final RestTemplateBuilder restTemplateBuilder) {
    val timeOut = 1000 * 60 * 30;
    return restTemplateBuilder.setConnectTimeout(timeOut).setReadTimeout(timeOut).build();
  }
}
