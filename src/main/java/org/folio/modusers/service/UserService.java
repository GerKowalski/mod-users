package org.folio.modusers.service;

import java.util.List;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.folio.modusers.convertors.impl.UserConverter;
import org.folio.modusers.dto.UserDto;
import org.folio.modusers.dto.UserdataCollectionDto;
import org.folio.modusers.entity.User;
import org.folio.modusers.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService
{
	private final UserRepository userRepository;

	private final UserConverter userConverter;

	public UserDto getUserById(String id)
	{
		return userRepository.findById(id)
					  .map(userConverter::convert)
					  .orElseThrow(() -> new IllegalArgumentException("User not found"));
	}

	public UserdataCollectionDto getUsers()
	{
		return userConverter.convertToCollection(userRepository.findAll());
	}

	public void removeById(final String id)
	{
		userRepository.deleteById(id);
	}

	public UserDto saveUser(final UserDto userDto)
	{
		User user = userConverter.convertToEntity(userDto);

		return userConverter.convert(userRepository.save(user));
	}


}
