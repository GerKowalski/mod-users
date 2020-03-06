package org.folio.modusers.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRequestInterceptor implements RequestInterceptor {

  @Autowired
  private HttpServletRequest request;

  @Override
  public void apply(RequestTemplate template) {
    if (request == null) {
      throw new IllegalStateException(
          "Outgoing request should be executed in the thread of ingoing request.");
    }
    template.headers(Collections
        .list(request.getHeaderNames())
        .stream()
        .collect(Collectors.toMap(
            Function.identity(),
            h -> Collections.list(request.getHeaders(h))
        )));
  }
}
