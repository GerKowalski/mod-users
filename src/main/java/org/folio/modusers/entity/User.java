package org.folio.modusers.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "users")
@Data
@Entity
public class User implements Serializable
{

	@Id
	@Column(name = "id")
	@GeneratedValue
	private UUID id;

	@Column(name = "username")
	private String username;

	@Column(name = "external_system_id")
	private UUID externalSystemId;

	@Column(name = "barcode")
	private String barcode;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "type")
	private String type;

	@Column(name = "patron_group_id")
	private UUID patronGroupId;

	//TODO Discussion with team
//	private Object meta;

/*	@Column("proxyFor")
	private List<String> proxyFor;*/

/*	@Column("personal")
	private UserPersonal personal;*/

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private List<Address> addresses;

	@Column(name = "enrollment_date")
	private Date enrollmentDate;

	@Column(name = "expiration_date")
	private Date expirationDate;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "middlename")
	private String middleName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "modile_phone")
	private String mobilePhone;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;

}