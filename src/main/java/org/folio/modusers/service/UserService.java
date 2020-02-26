package org.folio.modusers.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.folio.modusers.dto.UserDtoOld;
import org.folio.modusers.entity.User;
import org.folio.modusers.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final ConversionService conversionService;

  public UserDtoOld getUserById(String id) {
    return userRepository.findById(id)
        .map(u -> conversionService.convert(u, UserDtoOld.class))
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public List<UserDtoOld> getUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(this::convert)
        .collect(Collectors.toList());
  }

  private UserDtoOld convert(User user) {
    return conversionService.convert(user, UserDtoOld.class);
  }

  public void removeById(final String id) {
    userRepository.deleteById(id);
  }

  public UserDtoOld saveUser(final User user) {
    return convert(userRepository.save(user));
  }


}
