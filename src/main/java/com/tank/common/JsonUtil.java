package com.tank.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author fuchun
 */
@Component
@Slf4j
public class JsonUtil {

  public <T> Optional<String> toJsonStr(final @NonNull T obj) {
    val mapper = new ObjectMapper();
    try {
      return Optional.of(mapper.writeValueAsString(obj));
    } catch (JsonProcessingException e) {
      log.error(String.format("convert object to json str exception:%s", e.getMessage()));
      return Optional.empty();
    }
  }
}
