package org.folio.modusers.service;

import java.util.List;

import org.folio.modusers.entity.User;
import org.folio.modusers.dto.UserDTO;

public interface UserService
{
	UserDTO getUserById(String id);

	List<UserDTO> getUsers();

	void removeById(String id);

	UserDTO saveUser(User user);

}
