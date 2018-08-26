package com.tank.controller.login;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static com.tank.common.ErrorMessage.errorTips;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * @author fuchun
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/quickbi")
public class LoginController {


  @PostMapping("/login")
  public ResponseEntity login(@RequestBody Map<String, String> loginInfo) {
    val user = loginInfo.get("username");
    val password = loginInfo.get("password");
    val rs = Arrays.asList(check(user, "jack"::equals), check(password, "123"::equals))
        .stream()
        .reduce(Boolean::logicalAnd)
        .get();
    val response = rs ? ResponseEntity.ok().body(token(user)) : errorTips(FORBIDDEN, "user and password not correct");
    return response;
  }


  private Map<String, String> token(final String username) {
    val userToken = new HashMap<String, String>(16);
    val date = LocalDate.now();
    val sb = new StringBuilder(username);
    sb.append(date.getYear());
    val token = new String(Base64.getEncoder().encode(sb.toString().getBytes()));
    userToken.put("token", token);
    return userToken;
  }

  private <T> boolean check(T data, Predicate<T> predicate) {
    return predicate.test(data);
  }
}
