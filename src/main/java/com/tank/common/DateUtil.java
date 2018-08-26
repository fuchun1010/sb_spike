package com.tank.common;

import lombok.val;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author fuchun
 */
@Component
public class DateUtil {

  /**
   * convert date string to millions
   *
   * @param dateStr
   * @return
   */
  public long convertDateStrToMillions(final String dateStr) {
    val date = LocalDateTime.parse(dateStr, df);
    val millions = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    return millions;
  }

  /**
   * convert millions to date str
   *
   * @param millions
   * @return
   */
  public String toDateStrWithMillions(final long millions) {
    val instant = Instant.ofEpochMilli(millions);
    val dateStr = instant.atZone(ZoneId.systemDefault()).format(df);
    return dateStr;
  }

  private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

}
