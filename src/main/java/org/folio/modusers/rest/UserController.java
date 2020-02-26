package org.folio.modusers.rest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.folio.modusers.dto.UserDtoOld;
import org.folio.modusers.entity.User;
import org.folio.modusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-poc/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "{id}")
  public UserDtoOld getUserById(@PathVariable final String id) {
    return userService.getUserById(id);
  }

  @GetMapping
  public List<UserDtoOld> getUsers() {
    return userService.getUsers();
  }

  @DeleteMapping(value = "{id}")
  public void deleteUserById(@PathVariable final String id) {
    userService.removeById(id);
  }

  @PostMapping
  public UserDtoOld saveOrUpdateUser(@RequestBody final User user) {
    return userService.saveUser(user);
  }

}
