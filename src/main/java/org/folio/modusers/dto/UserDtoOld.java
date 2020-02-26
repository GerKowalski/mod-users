package org.folio.modusers.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Builder
public class UserDtoOld implements Serializable
{
	private String jsonb;

	private String creationDate;

	private String createdBy;

	private String patronGroup;

}
