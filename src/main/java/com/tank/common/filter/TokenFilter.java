package com.tank.common.filter;

import com.tank.common.JsonUtil;
import com.tank.common.UrlPrefix;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author fuchun
 * check login token
 */
@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = UrlPrefix.api + "*")
public class TokenFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("...init loginFilter....");
  }

  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain chain) throws IOException, ServletException {

    final HttpServletRequest req = (HttpServletRequest) servletRequest;
    final HttpServletResponse res = (HttpServletResponse) servletResponse;
    val token = req.getHeader("authorization");
    log.info(String.format("fetch token is: %s", token));
    if (!Objects.isNull(token)) {
      chain.doFilter(servletRequest, servletResponse);
    } else {
      try {
        val writer = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        val map = new HashMap<String, Object>();
        map.put("code", 401);
        map.put("message", "illegal access");
        this.jsonUtil.toJsonStr(map).ifPresent(json -> {
          writer.print(json);
          writer.flush();
        });
      } catch (Exception e) {
        log.error(String.format("check login token exception:%s", e.getMessage()));
      }
    }

  }

  @Override
  public void destroy() {
    log.info("....destroy login filter loaded resource");
  }

  @Autowired
  private JsonUtil jsonUtil;

}
