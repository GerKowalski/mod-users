package org.folio.modusers.rest;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.folio.modusers.blusers.api.BlUsersApi;
import org.folio.modusers.dto.CompositeUserDto;
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
}
