package com.letscode.rebeldes.service;

import com.letscode.rebeldes.dto.Trade;
import com.letscode.rebeldes.model.Item;
import com.letscode.rebeldes.model.Location;
import com.letscode.rebeldes.model.User;

import java.util.List;

public interface UserServiceInterface {
  User createUser(User user);
  void deleteUser(Long userId);
  User getUserById(Long userId);
  List<User> getAllUsers();
  User updateUserLocation(Long userId, Location location);
  User reportUser(Long userId);
  String tradeUsersItems(Long userOneId, Long userTwoId, Trade trade);
}
