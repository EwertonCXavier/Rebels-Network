package com.letscode.rebeldes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.letscode.rebeldes.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserCreateDTO(
    @NotNull
    String nome,
    @NotNull
    Long idade,
    @NotNull
    String genero,
    @NotNull
    Location location
) {
}
