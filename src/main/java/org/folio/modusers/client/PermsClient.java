package org.folio.modusers.client;

import java.util.List;
import org.folio.modusers.dto.FullPermissions;
import org.folio.modusers.dto.PermissionUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("perms/users")
public interface PermsClient {

  PermissionUserDto getPerms(@RequestParam("query") String query);

  FullPermissions getFullPerms(@RequestParam String id);
}
