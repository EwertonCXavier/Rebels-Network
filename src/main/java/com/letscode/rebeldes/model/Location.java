package com.letscode.rebeldes.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO
 *
 * Pendente:

 * 1. Inserir a relação entre as entidades Rebelde e Localização

 **/

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  // O nome definido aqui deve ser o mesmo da entidade User
  @Column(name = "locationid")
  private Long id;
  @NotNull
  private String nome;
  @NotNull
  private String latitude;
  @NotNull
  private String longitude;
  private Date createdAt;
  private Date updatedAt;

  @PrePersist
  public void preSave() {
    updatedAt = new Date();
    createdAt = new Date();
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = new Date();
  }
}
