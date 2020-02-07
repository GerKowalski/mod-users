package org.folio.modusers.rest;

import java.util.List;

import org.folio.modusers.entity.User;
import org.folio.modusers.dto.UserDTO;
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
public class UserAPI
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/user/{id}")
	public UserDTO getUserById(@PathVariable final String id)
	{
		return userService.getUserById(id);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getUsers()
	{
		List<UserDTO> users = userService.getUsers();

		return new ResponseEntity<>(
				users,
				new HttpHeaders(),
				HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/{id}")
	public void deleteUserById(@PathVariable final String id)
	{
		userService.removeById(id);
	}

	@PostMapping(value = "/user")
	public UserDTO updateUser(@RequestBody final User user)
	{
		return userService.saveUser(user);
	}

}
