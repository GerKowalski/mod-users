package org.folio.modusers.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

import static javax.persistence.FetchType.EAGER;

@Table(name = "address")
@Data
@Entity
public class Address implements Serializable {

  @Id
  @GeneratedValue
  private UUID id;

	@ManyToOne(fetch = EAGER)
	@JoinColumn(name = "user_id")
	private User user;

//	private AddressType addressTypeId;

  private String countryId;

  private String addressLine1;

  private String addressLine2;

  private String city;

  private String region;

  private String postalCode;

  private boolean primaryAddress;


}
