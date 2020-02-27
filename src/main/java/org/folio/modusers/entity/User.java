package org.folio.modusers.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "users")
@Data
public class User implements Persistable<UUID>, Serializable
{
	@Id
	@Column("id")
	private UUID id;

	@Column("username")
	private String username;
	
	@Column("external_system_id")
	private UUID externalSystemId;

	@Column("barcode")
	private String barcode;

	@Column("active")
	private Boolean active;

	@Column("type")
	private String type;

	@Column("patron_group_id")
	private UUID patronGroupId;

	//TODO Discussion with team
//	private Object meta;

/*	@Column("proxyFor")
	private List<String> proxyFor;*/

/*	@Column("personal")
	private UserPersonal personal;*/

	@Column("enrollment_date")
	private Date enrollmentDate;

	@Column("expiration_date")
	private Date expirationDate;

	@Column("created_date")
	private Date createdDate;

	@Column("updated_date")
	private Date updatedDate;

	@Column("lastname")
	private String lastName;

	@Column("firstname")
	private String firstName;

	@Column("middlename")
	private String middleName;

	@Column("email")
	private String email;

	@Column("phone")
	private String phone;

	@Column("modile_phone")
	private String mobilePhone;

	@Column("date_of_birth")
	private Date dateOfBirth;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}

}