package org.folio.modusers.rest;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.folio.modusers.blusers.api.BlUsersApi;
import org.folio.modusers.dto.CompositeUserDto;
import org.folio.modusers.dto.CompositeUserListObjectDto;
import org.folio.modusers.service.CompositeUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompositeUserController implements BlUsersApi {

  private final CompositeUserService compositeUserService;

  @Override
  public ResponseEntity<CompositeUserDto> getBlUserById(String userId, @Valid List<String> include,
      @Valid Boolean expandPermissions) {
    return ResponseEntity.ok(compositeUserService.getUserById(userId, include, expandPermissions));
  }

  @Override
  public ResponseEntity<CompositeUserListObjectDto> getBlUsers(@Valid String query,
      @Valid List<String> include, @Min(0) @Max(2147483647) @Valid Integer offset,
      @Min(0) @Max(2147483647) @Valid Integer limit) {
    return ResponseEntity.ok(compositeUserService.getUsers(offset, limit, include));
  }
}
