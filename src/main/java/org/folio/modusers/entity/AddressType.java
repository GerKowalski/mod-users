package org.folio.modusers.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "address_type")
@Data
@Entity
public class AddressType implements Serializable
{

	@Id
	@Column(name = "id")
	private UUID id;

	@Column(name = "address_type")
	private String addressType;

	@Column(name = "description")
	private String description;

}

