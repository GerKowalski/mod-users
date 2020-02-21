package org.folio.modusers.rest;

import java.util.List;

import org.folio.modusers.entity.User;
import org.folio.modusers.dto.UserDtoOld;
import org.folio.modusers.repository.UserRepository;
import org.folio.modusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-poc")
public class UserAPI
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/users/{id}")
	public UserDtoOld getUserById(@PathVariable final String id)
	{
		return userService.getUserById(id);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDtoOld>> getUsers()
	{
		List<UserDtoOld> users = userService.getUsers();

		return new ResponseEntity<>(
				users,
				new HttpHeaders(),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/{id}")
	public void deleteUserById(@PathVariable final String id)
	{
		userService.removeById(id);
	}

	@PostMapping(value = "/users")
	public UserDtoOld updateUser(@RequestBody final User user)
	{
		return userService.saveUser(user);
	}

}
