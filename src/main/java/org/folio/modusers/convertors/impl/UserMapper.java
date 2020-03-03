package org.folio.modusers.convertors.impl;

import org.folio.modusers.dto.UserDto;
import org.folio.modusers.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface UserMapper {
    @Mappings({
            @Mapping(target = "id", expression = "java(String.valueOf(user.getId()))"),
            @Mapping(target = "externalSystemId", expression = "java(String.valueOf(user.getExternalSystemId()))"),
            @Mapping(target = "personal.firstName", source="firstName"),
            @Mapping(target = "personal.lastName", source="lastName"),
            @Mapping(target = "personal.middleName", source="middleName"),
            @Mapping(target = "personal.email", source="email"),
            @Mapping(target = "personal.phone", source="phone"),
            @Mapping(target = "personal.mobilePhone", source="mobilePhone"),
            @Mapping(target = "personal.dateOfBirth", source="dateOfBirth"),
            @Mapping(target = "personal.addresses", source="addresses")

    })
    UserDto mapEntityToDto(User user);

    @Mappings({
            @Mapping(target = "id", expression = "java(java.util.UUID.fromString(userDto.getId()))"),
            @Mapping(target = "externalSystemId", expression = "java(java.util.UUID.fromString(userDto.getId()))"),
    })
    @InheritInverseConfiguration
    User mapDtoToEntity(UserDto userDto);
}
