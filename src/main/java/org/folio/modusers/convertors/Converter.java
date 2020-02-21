package org.folio.modusers.convertors;

import org.folio.modusers.dto.UserDtoOld;
import org.springframework.stereotype.Component;

@Component
public interface Converter<SOURCE>
{
	UserDtoOld convert(SOURCE source);
}
