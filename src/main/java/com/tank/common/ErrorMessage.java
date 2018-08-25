package com.tank.common;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ErrorMessage {

  public static ResponseEntity errorTips(final Throwable e) {
    val map = new HashMap<String,Object>();
    map.put("code", 500);
    map.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
  }
}
