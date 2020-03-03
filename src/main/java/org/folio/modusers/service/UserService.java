package org.folio.modusers.service;

import lombok.RequiredArgsConstructor;
import org.folio.modusers.convertors.impl.UserConverter;
import org.folio.modusers.convertors.impl.UserMapper;
import org.folio.modusers.dto.UserDto;
import org.folio.modusers.dto.UserdataCollectionDto;
import org.folio.modusers.entity.User;
import org.folio.modusers.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserConverter userConverter;

    public UserDto getUserById(String id) {
        return userRepository.findById(UUID.fromString(id))
                .map(userMapper::mapEntityToDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public UserdataCollectionDto getUsers() {
        return userConverter.convertToCollection(userRepository.findAll());
    }

    public void removeById(final String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    public UserDto saveUser(final UserDto userDto) {
        User user = userMapper.mapDtoToEntity(userDto);
        return userMapper.mapEntityToDto(userRepository.save(user));
    }


}
