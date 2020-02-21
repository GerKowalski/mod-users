package org.folio.modusers.service;

import java.util.List;

import org.folio.modusers.entity.User;
import org.folio.modusers.dto.UserDtoOld;

public interface UserService
{
	UserDtoOld getUserById(String id);

	List<UserDtoOld> getUsers();

	void removeById(String id);

	UserDtoOld saveUser(User user);

}
