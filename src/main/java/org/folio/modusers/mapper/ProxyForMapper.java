package org.folio.modusers.mapper;

import java.util.List;
import org.folio.modusers.dto.ProxyForDto;
import org.folio.modusers.dto.ProxyforCollectionDto;
import org.folio.modusers.entity.ProxyFor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProxyForMapper {

  @Mappings({
      @Mapping(target = "id", expression = "java(proxyFor.getId() == null ? null : String.valueOf(proxyFor.getId()))"),
  })
  ProxyForDto mapEntityToDto(ProxyFor proxyFor);

  @Mappings({
      @Mapping(target = "id", expression = "java(proxyForDto.getId() == null ? null : java.util.UUID.fromString(proxyForDto.getId()))"),
  })
  ProxyFor mapDtoToEntity(ProxyForDto proxyForDto);


  @Mappings({})
  List<ProxyForDto> mapEntitiesToDtos(List<ProxyFor> proxyFors);

  @InheritInverseConfiguration
  List<ProxyFor> mapDtosToEntities(List<ProxyForDto> proxyForDtos);

  default ProxyforCollectionDto mapToUserDataCollectionDto(List<ProxyFor> proxyFors) {
    ProxyforCollectionDto proxyforCollectionDto = new ProxyforCollectionDto();
    List<ProxyForDto> proxyForDtos = mapEntitiesToDtos(proxyFors);
    proxyforCollectionDto.setProxiesFor(proxyForDtos);
    proxyforCollectionDto.setTotalRecords(proxyForDtos.size());
    return proxyforCollectionDto;
  }

  default List<ProxyFor> mapProxyForCollectionDtoToEntity(
      ProxyforCollectionDto proxyforCollectionDto) {
    return mapDtosToEntities(proxyforCollectionDto.getProxiesFor());
  }

  @Mappings({
      @Mapping(target = "id", expression = "java(proxyFor.getId() == null ? null : java.util.UUID.fromString(proxyForDto.getId()))"),
  })
  void mapEntityToDto(ProxyForDto proxyForDto, @MappingTarget ProxyFor proxyFor);

}
