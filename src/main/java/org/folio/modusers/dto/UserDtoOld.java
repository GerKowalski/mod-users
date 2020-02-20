package org.folio.modusers.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
public class UserDtoOld implements Serializable
{
	private String jsonb;

	private String creationDate;

	private String createdBy;

	private String patronGroup;

}
