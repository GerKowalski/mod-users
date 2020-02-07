package org.folio.modusers.convertors.impl;

import org.folio.modusers.convertors.Converter;
import org.folio.modusers.entity.User;
import org.folio.modusers.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User>
{

	@Override
	public UserDTO convert(final User user)
	{
		return new UserDTO()
				.setJsonb(user.getJsonb())
				.setCreationDate(user.getCreationDate())
				.setCreatedBy(user.getCreatedBy())
				.setPatronGroup(user.getPatronGroup());
	}
}
