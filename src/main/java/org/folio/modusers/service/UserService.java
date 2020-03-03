package org.folio.modusers.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.folio.modusers.dto.UserDto;
import org.folio.modusers.dto.UserdataCollectionDto;
import org.folio.modusers.entity.User;
import org.folio.modusers.mapper.UserMapper;
import org.folio.modusers.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserDto getUserById(String id) {
    return userRepository.findById(UUID.fromString(id))
        .map(userMapper::mapEntityToDto)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public UserdataCollectionDto getUsers() {
    return userMapper.mapToUserDataCollectionDto(userRepository.findAll());
  }

  public void removeById(final String id) {
    userRepository.deleteById(UUID.fromString(id));
  }

  public UserDto saveUser(final UserDto userDto) {
    User user = userMapper.mapDtoToEntity(userDto);
    return userMapper.mapEntityToDto(userRepository.save(user));
  }

    public void updateUser(final UserDto userDto,final String userId ) {
        User user = userRepository.getOne(UUID.fromString(userId));
        userMapper.mapEntityToDto(userDto, user);
        userRepository.save(user);
    }
}
