package org.folio.modusers.service;

import javax.servlet.http.HttpServletRequest;
import org.folio.modusers.domain.OkapiHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OkapiService {

  @Autowired
  private HttpServletRequest request;

  public OkapiHeaders getOkapiHeaders() {
    /*request.headers().map().entrySet().stream().filter(e -> e.getKey().startsWith(
        OkapiHeaders.PREFIX)).map(e-)*/
    OkapiHeaders okapiHeaders = new OkapiHeaders();
    return okapiHeaders;
  }

}
