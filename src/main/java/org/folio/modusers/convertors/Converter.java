package org.folio.modusers.convertors;

import org.folio.modusers.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public interface Converter<SOURCE>
{
	UserDTO convert(SOURCE source);
}
