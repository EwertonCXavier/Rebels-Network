package com.letscode.rebeldes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.letscode.rebeldes.model.Item;
import com.letscode.rebeldes.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserCreateDTO(
    @NotNull(message = "name must be provided!")
    String nome,
    @NotNull(message = "age must be provided!")
    Long idade,
    @NotNull(message = "genre must be provided!")
    String genero,
    @NotEmpty
    @NotNull(message = "location must be provided!")
    Location location,
    @NotBlank
    @NotNull
    @NotEmpty
    Item inventory
) {
}
