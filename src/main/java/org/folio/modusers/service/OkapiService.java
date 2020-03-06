package org.folio.modusers.service;

import java.net.http.HttpRequest;
import org.folio.modusers.domain.OkapiHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OkapiService {

  @Autowired
  private HttpRequest request;

  public OkapiHeaders getOkapiHeaders() {
    request.headers().map().entrySet().stream().filter(e -> e.getKey().startsWith(
        OkapiHeaders.PREFIX)).map(e-)
    OkapiHeaders okapiHeaders = new OkapiHeaders();

  }

}
