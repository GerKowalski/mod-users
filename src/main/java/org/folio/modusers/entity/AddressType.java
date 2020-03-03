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
public class AddressType implements Serializable {

  @Id
  private UUID id;

  private String addressType;

  private String description;

}

