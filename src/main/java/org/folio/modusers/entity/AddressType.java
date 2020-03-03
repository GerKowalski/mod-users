package org.folio.modusers.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

import lombok.Data;

@Table(name = "address_type")
@Data
@Entity
public class AddressType implements Serializable {

  @Id
  @GeneratedValue
  private UUID id;

  private String addressType;

  private String description;

}

