package org.folio.modusers.convertors.impl;

import org.folio.modusers.convertors.Converter;
import org.folio.modusers.entity.User;
import org.folio.modusers.dto.UserDtoOld;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User>
{

	@Override
	public UserDtoOld convert(final User user)
	{
		return new UserDtoOld()
				.setJsonb(user.getJsonb())
				.setCreationDate(user.getCreationDate())
				.setCreatedBy(user.getCreatedBy())
				.setPatronGroup(user.getPatronGroup());
	}
}
