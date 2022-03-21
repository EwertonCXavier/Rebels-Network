package com.letscode.rebeldes.controller;

import com.letscode.rebeldes.converter.UserMapper;
import com.letscode.rebeldes.dto.UserCreateDTO;
import com.letscode.rebeldes.dto.UserDTO;
import com.letscode.rebeldes.model.Location;
import com.letscode.rebeldes.model.User;
import com.letscode.rebeldes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping
  public List<UserDTO> getAllUsers(){
    var list = userService.getAllUsers().stream().map(
        userMapper::userToUserDTO
    ).toList();
    return list;
  }

  @GetMapping("/{id}")
  public UserDTO getById(@PathVariable Long id) {
    var userDTO = userMapper.userToUserDTO(userService.getUserById(id));
    return userDTO;
  }

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
    var user = userService.createUser(userMapper.userCreateDTOToUser(userCreateDTO));
    return ResponseEntity.ok(userMapper.userToUserDTO(user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}/location")
  public ResponseEntity<UserDTO> updateLocation(@PathVariable Long id, @RequestBody Location location) {
    var newLocation = userService.updateUserLocation(id, location);
    return ResponseEntity.ok(userMapper.userToUserDTO(newLocation));
  }

  @PatchMapping("/{id}/report")
  public ResponseEntity<User> updateReportCount(@PathVariable Long id) {
    return ResponseEntity.ok(userService.reportUser(id));
  }


}
