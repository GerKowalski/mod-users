package org.folio.modusers.client;

import org.folio.modusers.dto.CredentialsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("credentials")
public interface CredentialsClient extends QueringClient<CredentialsDto> {

  @GetMapping("/authn/credentials")
  CredentialsDto get(@RequestParam String query);

}
