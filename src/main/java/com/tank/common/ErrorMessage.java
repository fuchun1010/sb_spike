package com.tank.common;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {

  public static ResponseEntity errorTips(final Throwable e) {
    val map = new HashMap<String, Object>(16);
    map.put("code", 500);
    map.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
  }

  public static ResponseEntity errorTips(final HttpStatus status, final String message) {
    final Map<String, Object> tips = new HashMap<>(16);
    tips.put("code", status.value());
    tips.put("message", message);
    return ResponseEntity.status(status).body(tips);
  }
}
