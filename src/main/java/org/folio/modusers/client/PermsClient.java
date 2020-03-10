package org.folio.modusers.client;

import org.folio.modusers.dto.FullPermissions;
import org.folio.modusers.dto.PermissionUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("perms")
public interface PermsClient {

  @GetMapping("perms/users")
  PermissionUserDto getPerms(@RequestParam("query") String query);

  @GetMapping("perms/users")
  FullPermissions getFullPerms(@RequestParam String id);
}
