package org.folio.modusers.dto;

import java.util.List;
import lombok.Data;
import org.folio.modusers.dto.PermissionUserDto;

@Data
public class PermissionUser {

  private List<PermissionUserDto> permissions;

  private String id;

}
