package org.folio.modusers.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.folio.modusers.domain.OffsetRequest;
import org.folio.modusers.dto.UserDto;
import org.folio.modusers.dto.UserdataCollectionDto;
import org.folio.modusers.entity.User;
import org.folio.modusers.mapper.UserMapper;
import org.folio.modusers.repository.UserRepository;
import org.springframework.data.domain.Page;
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

  public List<UserDto> getUsersByOffset(int offset, int limit) {
    Page<User> users = userRepository.findAll(new OffsetRequest(offset, limit));
    return users.stream().map(userMapper::mapEntityToDto).collect(Collectors.toList());
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


}
