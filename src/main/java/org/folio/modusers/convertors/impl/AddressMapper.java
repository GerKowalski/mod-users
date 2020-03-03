package org.folio.modusers.convertors.impl;

import org.folio.modusers.dto.UserPersonalAddressesDto;
import org.folio.modusers.entity.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mappings({
            @Mapping(target = "id", expression = "java(String.valueOf(address.getId()))")
    })
    UserPersonalAddressesDto mapEntityToDto(Address address);

    @Mappings({
            @Mapping(target = "id", expression =  "java(java.util.UUID.fromString(addressesDto.getId()))")
    })
    @InheritInverseConfiguration
    Address mapDtoToEntity(UserPersonalAddressesDto addressesDto);
}
