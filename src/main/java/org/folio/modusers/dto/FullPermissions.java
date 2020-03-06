package org.folio.modusers.dto;

import java.util.List;
import lombok.Data;

@Data
public class FullPermissions {

  private List<PermissionUserDto> permissionNames;

}
