package com.letscode.rebeldes.converter;

import com.letscode.rebeldes.dto.UserCreateDTO;
import com.letscode.rebeldes.dto.UserDTO;
import com.letscode.rebeldes.model.User;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {

  UserDTO userToUserDTO(User user);

  User userCreateDTOToUser(UserCreateDTO userCreateDTO);
}
