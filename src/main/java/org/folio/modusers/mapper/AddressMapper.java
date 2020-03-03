package org.folio.modusers.mapper;

import org.folio.modusers.dto.UserPersonalAddressesDto;
import org.folio.modusers.entity.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AddressMapper {
    @Mappings({
            @Mapping(target = "id", expression = "java(address.getId() == null ? null : String.valueOf(address.getId()"
                                                 + "))")

    })
    UserPersonalAddressesDto mapEntityToDto(Address address);

    @Mappings({
            @Mapping(target = "id", expression = "java(addressesDto.getId() == null ? null : java.util.UUID.fromString"
                                                 + "(addressesDto.getId()))")


    })
    @InheritInverseConfiguration
    Address mapDtoToEntity(UserPersonalAddressesDto addressesDto);
}
