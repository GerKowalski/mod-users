package org.folio.modusers.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "users")
@Data
public class User implements Serializable
{
	@Id
	private String id;

	private String jsonb;

	private String creationDate;

	private String createdBy;

	private String patronGroup;

}