package org.folio.modusers.mapper;

import org.folio.modusers.dto.AddresstypeCollectionDto;
import org.folio.modusers.dto.AddresstypeDto;
import org.folio.modusers.entity.AddressType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AddressTypeMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java(addressType.getId() == null ? null : String.valueOf(addressType.getId()))"),
            @Mapping(target = "desc", source = "description")
    })
    AddresstypeDto mapEntityToDto(AddressType addressType);

    @Mappings({
            @Mapping(target = "id", expression = "java(addresstypeDto.getId() == null ? null : java.util.UUID.fromString(addresstypeDto.getId()))"),
            @Mapping(target = "description", source = "desc")
    })
    AddressType mapDtoToEntity(AddresstypeDto addresstypeDto);


    @Mappings({})
    List<AddresstypeDto> mapEntitiesToDtos(List<AddressType> addressTypes);

    @InheritInverseConfiguration
    List<AddressType> mapDtosToEntities(List<AddresstypeDto> addresses);

    default AddresstypeCollectionDto mapToUserDataCollectionDto(List<AddressType> addressTypes) {
        AddresstypeCollectionDto userdataCollectionDto = new AddresstypeCollectionDto();
        List<AddresstypeDto> userDtoList = mapEntitiesToDtos(addressTypes);
        userdataCollectionDto.setAddressTypes(userDtoList);
        userdataCollectionDto.setTotalRecords(userDtoList.size());
        return userdataCollectionDto;
    }

    default List<AddressType> mapAddressTypeCollectionDtoToEntity(AddresstypeCollectionDto addresstypeCollectionDto) {
        return mapDtosToEntities(addresstypeCollectionDto.getAddressTypes());
    }

    @Mappings({
            @Mapping(target = "id", expression = "java(addressType.getId() == null ? null : java.util.UUID.fromString(addressTypeDto.getId()))"),
            @Mapping(target = "description", source = "desc")
    })
    void mapEntityToDto(AddresstypeDto addressTypeDto, @MappingTarget AddressType addressType);
}
