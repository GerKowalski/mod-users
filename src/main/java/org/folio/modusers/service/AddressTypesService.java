package org.folio.modusers.service;

import lombok.RequiredArgsConstructor;
import org.folio.modusers.dto.AddresstypeCollectionDto;
import org.folio.modusers.dto.AddresstypeDto;
import org.folio.modusers.entity.AddressType;
import org.folio.modusers.repository.AddressTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressTypesService
{
	private AddressTypeRepository addressTypeRepository;

	public AddresstypeCollectionDto getAddressTypes()
	{
		Iterable<AddressType> addressTypes = addressTypeRepository.findAll();
		//TODO add convert
		return null;
	}

	public void deleteAddressTypes(final String addressTypeId)
	{
		addressTypeRepository.deleteById(UUID.fromString(addressTypeId));
	}

	public AddresstypeDto getAddressTypeById(final String addressTypeId)
	{
		Optional<AddressType> addressType = addressTypeRepository.findById(UUID.fromString(addressTypeId));
		//TODO add convert
		return null;
	}

	public AddresstypeDto saveAddressTypes(final AddresstypeDto addressType)
	{
		//TODO add convert
		return null;
	}
}
