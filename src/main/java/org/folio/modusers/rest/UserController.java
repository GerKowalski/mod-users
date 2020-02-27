package org.folio.modusers.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.RequiredArgsConstructor;
import org.folio.modusers.dto.UserDto;
import org.folio.modusers.dto.UserdataCollectionDto;
import org.folio.modusers.service.UserService;
import org.folio.modusers.users.api.UsersApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-poc")
@RequiredArgsConstructor
public class UserController implements UsersApi
{
	private final UserService userService;

	@Override
	public ResponseEntity<UserDto> postUsers(@Valid final UserDto userdata,
											 @Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		return new ResponseEntity<>(userService.saveUser(userdata), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deleteUsersByUserId(final String userId,
													@Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		userService.removeById(userId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<UserdataCollectionDto> getUsers(@Valid final String query,
														  @Min(0) @Max(2147483647) @Valid final Integer offset,
														  @Min(0) @Max(2147483647) @Valid final Integer limit,
														  @Valid final String orderBy, @Valid final String order,
														  @Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang,
														  @Valid final List<String> facets)
	{
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDto> getUsersByUserId(final String userId,
													@Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> putUsersByUserId(final String userId, @Valid final UserDto userdataCollection,
												 @Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		userService.saveUser(userdataCollection);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
