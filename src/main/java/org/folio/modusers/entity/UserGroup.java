package org.folio.modusers.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "user_group")
@Data
@Entity
public class UserGroup implements Serializable {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "name")
  private String group;

  private String description;

  @OneToOne(mappedBy = "userGroup")
  private User user;
}
