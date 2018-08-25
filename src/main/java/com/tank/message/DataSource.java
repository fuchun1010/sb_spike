package com.tank.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

/**
 * @author fuchun
 */
@Data
public class DataSource {

  private String ip;

  @JsonInclude(NON_DEFAULT)
  private Optional<Integer> port;

}
