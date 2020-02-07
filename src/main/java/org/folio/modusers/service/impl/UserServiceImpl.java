package org.folio.modusers.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.folio.modusers.convertors.impl.UserConverter;
import org.folio.modusers.entity.User;
import org.folio.modusers.dto.UserDTO;
import org.folio.modusers.repository.UserRepository;
import org.folio.modusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserConverter userConverter;

	@Override
	public UserDTO getUserById(String id)
	{
		Optional<User> user = userRepository.findById(id);

		return userConverter.convert(user.get());
	}

	@Override
	public List<UserDTO> getUsers()
	{
		List<User> users = userRepository.findAll();

		return users.stream()
					.map(userConverter::convert)
					.collect(Collectors.toList());
	}

	@Override
	public void removeById(final String id)
	{
		userRepository.deleteById(id);
	}

	@Override
	public UserDTO saveUser(final User user)
	{
		return userConverter.convert(userRepository.save(user));
	}


}
