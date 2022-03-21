package com.letscode.rebeldes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.letscode.rebeldes.model.Location;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDTO(
    int id,
    String nome,
    int idade,
    String genero,
    @JsonIgnore
    Date createdAt,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Location location,
    boolean isTraitor,
    int reportCount
) {
}
