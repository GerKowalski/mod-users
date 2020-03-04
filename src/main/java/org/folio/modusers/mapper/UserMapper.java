package org.folio.modusers.mapper;

import java.util.List;
import org.folio.modusers.dto.UserDto;
import org.folio.modusers.dto.UserdataCollectionDto;
import org.folio.modusers.entity.User;
import org.mapstruct.*;


@Mapper(componentModel = "spring", uses = AddressMapper.class)
public abstract class UserMapper {

  @Mappings({
      @Mapping(target = "id", expression = "java(user.getId() == null ? null : String.valueOf(user.getId()))"),
      @Mapping(target = "externalSystemId", expression =
          "java(user.getExternalSystemId() == null ? null : String"
              + ".valueOf(user.getExternalSystemId()))"),
      @Mapping(target = "personal.firstName", source = "firstname"),
      @Mapping(target = "personal.lastName", source = "lastname"),
      @Mapping(target = "personal.middleName", source = "middlename"),
      @Mapping(target = "personal.email", source = "email"),
      @Mapping(target = "personal.phone", source = "phone"),
      @Mapping(target = "personal.mobilePhone", source = "mobilePhone"),
      @Mapping(target = "personal.dateOfBirth", source = "dateOfBirth"),
      @Mapping(target = "personal.addresses", source = "addresses")

  })
  public abstract UserDto mapEntityToDto(User user);

  @Mappings({
      @Mapping(target = "id", expression =
          "java(userDto.getId() == null ? null : java.util.UUID.fromString"
              + "(userDto.getId()))"),
      @Mapping(target = "externalSystemId", expression =
          "java(userDto.getId() == null ? null : java.util.UUID"
              + ".fromString(userDto.getId()))"),
  })
  @InheritInverseConfiguration
  public abstract User mapDtoToEntity(UserDto userDto);

  @Mappings({})
  public abstract List<UserDto> mapEntitiesToDtos(List<User> users);

  @InheritInverseConfiguration
  public abstract List<User> mapDtosToEntities(List<UserDto> users);

  public UserdataCollectionDto mapToUserDataCollectionDto(List<User> users) {
    UserdataCollectionDto userdataCollectionDto = new UserdataCollectionDto();
    List<UserDto> userDtoList = mapEntitiesToDtos(users);
    userdataCollectionDto.setUsers(userDtoList);
    userdataCollectionDto.setTotalRecords(userDtoList.size());
    return userdataCollectionDto;
  }

  public List<User> mapUserDataCollectionDtoToEntity(UserdataCollectionDto userdataCollectionDto) {
    return mapDtosToEntities(userdataCollectionDto.getUsers());
  }

  @Mappings({
      @Mapping(target = "id", expression = "java(user.getId() == null ? null : java.util.UUID.fromString(userDto.getId()))"),
      @Mapping(target = "externalSystemId", expression =
          "java(userDto.getId() == null ? null : java.util.UUID"
              + ".fromString(userDto.getId()))")
  })
  public abstract void mapEntityToDto(UserDto userDto, @MappingTarget User user);


}
