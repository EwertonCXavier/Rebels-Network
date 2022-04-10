package com.letscode.rebeldes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.letscode.rebeldes.model.Item;
import com.letscode.rebeldes.model.Location;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Set;

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
    boolean traitor,
    int reportCount,
    Item inventory
) {
}
