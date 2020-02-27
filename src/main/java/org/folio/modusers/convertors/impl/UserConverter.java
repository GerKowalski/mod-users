package org.folio.modusers.convertors.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.folio.modusers.dto.UserDto;
import org.folio.modusers.dto.UserPersonalDto;
import org.folio.modusers.dto.UserdataCollectionDto;
import org.folio.modusers.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDto>
{

	@Override
	public UserDto convert(final User user)
	{
		UserDto userDto = new UserDto();

		userDto.setId(String.valueOf(user.getId()));
		if (Objects.nonNull(user.getPatronGroupId()))
		{
			userDto.setPatronGroup(user.getPatronGroupId().toString());
		}

		userDto.setUsername(user.getUsername());
		userDto.setBarcode(user.getBarcode());
		userDto.setActive(user.getActive());
		userDto.setType(user.getType());
		userDto.setEnrollmentDate(user.getEnrollmentDate());
		userDto.setCreatedDate(user.getCreatedDate());

		UserPersonalDto personalDto = new UserPersonalDto();
		personalDto.setLastName(user.getLastName());
		personalDto.setFirstName(user.getFirstName());
		personalDto.setEmail(user.getEmail());
		personalDto.setPhone(user.getPhone());
		personalDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setPersonal(personalDto);

		return userDto;
	}

	public User convertToEntity(UserDto userDto)
	{
		User user = new User();

		if (Objects.nonNull(userDto.getId()))
		{
			user.setId(UUID.fromString(userDto.getId()));
		}
		if (Objects.nonNull(userDto.getExternalSystemId()))
		{
			user.setExternalSystemId(UUID.fromString(userDto.getExternalSystemId()));
		}
		if (Objects.nonNull(userDto.getPatronGroup()))
		{
			user.setPatronGroupId(UUID.fromString(userDto.getPatronGroup()));
		}
		user.setUsername(userDto.getUsername());
		user.setBarcode(userDto.getBarcode());
		user.setActive(userDto.isActive());
		user.setType(userDto.getType());
		user.setEnrollmentDate(userDto.getEnrollmentDate());
		user.setCreatedDate(userDto.getCreatedDate());
		user.setLastName(userDto.getPersonal().getLastName());
		user.setFirstName(userDto.getPersonal().getFirstName());
		user.setEmail(userDto.getPersonal().getEmail());
		user.setPhone(userDto.getPersonal().getPhone());
		user.setDateOfBirth(userDto.getPersonal().getDateOfBirth());

		return user;
	}

	public UserdataCollectionDto convertToCollection(final Iterable<User> users)
	{
		UserdataCollectionDto userdataCollectionDto = new UserdataCollectionDto();

		List<UserDto> usersDto = StreamSupport.stream(users.spliterator(), false)
											  .map(this::convert)
											  .collect(Collectors.toList());

		userdataCollectionDto.setUsers(usersDto);
		userdataCollectionDto.setTotalRecords(usersDto.size());

		return userdataCollectionDto;

	}
}
