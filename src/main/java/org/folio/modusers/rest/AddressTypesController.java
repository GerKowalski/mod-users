package org.folio.modusers.rest;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.RequiredArgsConstructor;
import org.folio.modusers.addresstypes.api.AddresstypesApi;
import org.folio.modusers.dto.AddresstypeCollectionDto;
import org.folio.modusers.dto.AddresstypeDto;
import org.folio.modusers.service.AddressTypesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users-poc")
@RequiredArgsConstructor
public class AddressTypesController implements AddresstypesApi
{

	private final AddressTypesService addressTypesService;

	@Override
	public ResponseEntity<Void> deleteAddressTypeById(final String addresstypeId,
													  @Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<AddresstypeDto> getAddressTypeById(final String addresstypeId,
															 @Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		return new ResponseEntity<>(addressTypesService.getAddressTypeById(addresstypeId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AddresstypeCollectionDto> getAddressTypes(@Valid final String query,
																	@Min(0) @Max(2147483647) @Valid final Integer offset,
																	@Min(0) @Max(2147483647) @Valid final Integer limit,
																	@Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		return new ResponseEntity<>(addressTypesService.getAddressTypes(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AddresstypeDto> postAddressTypes(@Valid final AddresstypeDto addresstype,
														   @Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		return new ResponseEntity<>(addressTypesService.saveAddressTypes(addresstype), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> putAddressTypeById(final String addresstypeId,
												   @Valid final AddresstypeDto userdataCollection,
												   @Pattern(regexp = "[a-zA-Z]{2}") @Valid final String lang)
	{
		addressTypesService.saveAddressTypes(userdataCollection);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
