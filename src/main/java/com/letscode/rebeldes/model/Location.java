package com.letscode.rebeldes.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TODO
 * <p>
 * Pendente:
 * <p>
 * 1. Inserir a relação entre as entidades Rebelde e Localização
 **/

@Data
@Embeddable
public class Location {
  @NotNull(message = "location must be provided! - Null")
  @NotBlank(message = "location must be provided! - Blank")
  @NotEmpty(message = "locationName must be provided! - Empty")
  private String locationName;
  @NotNull(message = "latitude must be provided! - Null")
  @NotBlank(message = "latitude must be provided! - Blank")
  @NotEmpty(message = "latitude must be provided! - Empty")
  private String latitude;
  @NotNull(message = "longitude must be provided! - Null")
  @NotBlank(message = "longitude must be provided! - Blank")
  @NotEmpty(message = "longitude must be provided! - Empty")
  private String longitude;
}
