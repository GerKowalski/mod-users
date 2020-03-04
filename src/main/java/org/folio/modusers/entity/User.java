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
public class User implements Serializable {

  @Id
  @GeneratedValue
  private UUID id;

  private String username;

  private UUID externalSystemId;

  private String barcode;

  private Boolean active;

  private String type;

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

  private Date enrollmentDate;

  private Date expirationDate;

  private Date createdDate;

  private Date updatedDate;

  private String lastname;

  private String firstname;

  private String middlename;

  private String email;

  private String phone;

  private String mobilePhone;

  private Date dateOfBirth;

}

