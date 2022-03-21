package com.letscode.rebeldes.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NotNull(message="id must be provided!")
  private Long id;
  @NotNull(message="Name must be provided!")
  private String nome;
  @NotNull(message="Age must be provided!") @Min(0)
  private int idade;
//  private int numberOfReports;
  @NotNull(message="Sex must be provided!")
  private String genero;
  private Date createdAt;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "locationid")
  @NotNull(message = "Location must be provided!")
  private Location location;
  @Column(name ="istraitor")
  private boolean isTraitor;
  @Column(name="reportcount")
  private int reportCount;


  @PrePersist
  private void preSave() {
    this.reportCount = 0;
    createdAt = new Date();
  }


}
