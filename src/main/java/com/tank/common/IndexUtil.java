package com.tank.common;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author fuchun
 */
@Component
public class IndexUtil {

  public String indexUrl(final String indexName) {
    val sb = new StringBuilder(url);
    sb.append(":");
    sb.append(port);
    sb.append("/");
    sb.append(indexName);
    return sb.toString();
  }

  public String indexUrlWithId(final String indexName, final String id) {
    val sb = new StringBuilder(this.indexUrl(indexName));
    sb.append("/");
    sb.append("_doc");
    sb.append("/");
    sb.append(id);
    return sb.toString();
  }


  @Value("${elastic_search.port}")
  private int port;

  @Value("${elastic_search.url}")
  private String url;
}
