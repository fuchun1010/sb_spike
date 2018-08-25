package com.tank.handler;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({InvalidFormatException.class})
  public ResponseEntity handleError(final Exception exception) {

    val sb = new StringBuilder("json parse error:");
    val map = new HashMap<String, Object>(16);
    map.put("code", 400);
    if (!Objects.isNull(exception)) {
      val errors = exception.getLocalizedMessage().split(":");
      if (errors.length > 2) {
        sb.append(errors[1]);
      }
      map.put("message", sb.toString());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    } else {
      return null;
    }
  }

  @ExceptionHandler({InvalidDefinitionException.class})
  public ResponseEntity handleInvalidError(final Exception exception) {
    val map = new HashMap<String, Object>(16);
    map.put("code", 400);
    if (!Objects.isNull(exception)) {
      map.put("message", "json lack key");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
    return null;
  }

}
