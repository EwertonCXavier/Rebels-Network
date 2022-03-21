package com.letscode.rebeldes.service;

import com.letscode.rebeldes.exception.UserNotFoundException;
import com.letscode.rebeldes.model.Location;
import com.letscode.rebeldes.model.User;
import com.letscode.rebeldes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
  private final UserRepository userRepository;

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public void deleteUser(Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new UserNotFoundException();
    }
    userRepository.deleteById(userId);
  }

  @Override
  public User getUserById(Long userId) {
    var optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    }
    throw new UserNotFoundException();
  }

  @Override
  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  @Override
  public User updateUserLocation(Long userId, Location location) {
    var optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      optionalUser.get().setLocation(location);
    }
    return userRepository.save(optionalUser.get());
  }

  @Override
  public User reportUser(Long userId) {
    var optionalUser = userRepository.findById(userId);
    // Verifica se o usuário de id informado existe
    if (optionalUser.isPresent()) {
      User userReported = optionalUser.get();
      // Incrementa a contagem
      userReported.setReportCount(userReported.getReportCount() + 1);
      // Caso a contagem seja igual ou maior a 3, atribui o estado como sendo rebelde
      if(userReported.getReportCount() >= 3) {
        userReported.setTraitor(true);
      }
      // Atualizo o banco de dados com as informações inseridas (patch da contagem e status)
      return userRepository.save(userReported);
    }

    throw new UserNotFoundException();
  }

}
