package com.letscode.rebeldes.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;


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
  @NotNull(message = "id must be provided!")
  private Long id;
  @NotNull(message = "Name must be provided!")
  private String nome;
  @NotNull(message = "Age must be provided!")
  @Min(0)
  private int idade;
  //  private int numberOfReports;
  @NotNull(message = "Sex must be provided!")
  private String genero;
  @CreationTimestamp
  private Date createdAt;
  private boolean traitor;
  @Column(name = "reportcount")
  private int reportCount;

  @Embedded
  @Valid()
  private Item inventory;

  @Embedded
  @Valid()
  private Location location;

}
