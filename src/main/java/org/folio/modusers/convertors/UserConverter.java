package org.folio.modusers.convertors;

import org.folio.modusers.dto.UserDtoOld;
import org.folio.modusers.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDtoOld> {

  @Override
  public UserDtoOld convert(final User user) {
    return UserDtoOld.builder()
        .jsonb(user.getJsonb())
        .creationDate(user.getCreationDate())
        .createdBy(user.getCreatedBy())
        .patronGroup(user.getPatronGroup()).build();
  }
}
