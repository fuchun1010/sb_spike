package com.tank.controller.datasource;

import com.tank.common.IndexUtil;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author fuchun
 */
@RestController
@CrossOrigin
@RequestMapping("/datasource")
public class DataSourceController {

  @PostMapping("/create")
  public ResponseEntity create(@RequestBody @NonNull Map<String, Object> request) {
    ResponseEntity response = CompletableFuture.<String>supplyAsync(() -> request.get("name").toString())
        .handleAsync((indexName, e) -> {
          val url = indexUtil.indexUrl(indexName);
          System.out.println(url);
          val type = new HashMap<String, String>(16);
          type.put("type", "keyword");

          val properties = new HashMap<String, Map>();
          properties.put("job", type);
          properties.put("name", type);
          properties.put("salary", type);

          val _doc = new HashMap<String, Map>();
          _doc.put("properties", properties);

          val doc = new HashMap<String, Map>();
          doc.put("_doc", _doc);

          val schema = new HashMap<String, Map>();
          schema.put("mappings", doc);

          this.restTemplate.put(url, schema);

          return ResponseEntity.status(CREATED).build();
        })
        .exceptionally(e -> {
          val errorMsg = new HashMap<String, Object>();
          errorMsg.put("code", 500);
          errorMsg.put("message", e.getMessage());
          return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorMsg);
        }).join();
    return response;
  }

  @GetMapping("/list")
  public ResponseEntity list() {
    val url = "http://localhost:9200/customer/_search?pretty";
    val response = this.restTemplate.getForObject(url, Map.class);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}/record")
  public ResponseEntity findBy(@PathVariable @NonNull final String id) {
    ResponseEntity response = CompletableFuture.<String>supplyAsync(() -> this.indexUtil.indexUrlWithId("customer", id))
        .handleAsync((url, e) -> {
          val res = this.restTemplate.getForObject(url, Map.class);
          return ResponseEntity.ok(res.get("_source"));
        })
        .exceptionally(e -> {
          val errorMsg = new HashMap<String, Object>();
          errorMsg.put("code", 500);
          errorMsg.put("message", e.getMessage());
          return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorMsg);
        }).join();
    return response;
  }

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private IndexUtil indexUtil;
}
