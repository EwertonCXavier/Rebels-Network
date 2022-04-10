package com.letscode.rebeldes.service;

import com.letscode.rebeldes.dto.Trade;
import com.letscode.rebeldes.exception.InvalidTradeAmountException;
import com.letscode.rebeldes.exception.LocationNotFoundException;
import com.letscode.rebeldes.exception.UserNotFoundException;
import com.letscode.rebeldes.model.Item;
import com.letscode.rebeldes.model.Location;
import com.letscode.rebeldes.model.User;
import com.letscode.rebeldes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
  private final UserRepository userRepository;

  @Override
  public User createUser(User user) {
    if (
        user.getLocation().getLocationName() == null ||
            user.getLocation().getLatitude() == null ||
            user.getLocation().getLongitude() == null) {
      throw new LocationNotFoundException();
    }
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
      if(userReported.getReportCount() == 3) {
        userReported.setTraitor(true);
      }
      System.out.println("USER REPORTED: " + " : " +  userReported.isTraitor());
      // Atualizo o banco de dados com as informações inseridas (patch da contagem e status)
      return userRepository.save(userReported);
    }

    throw new UserNotFoundException();
  }

  @Override
  public String tradeUsersItems(Long userOneId, Long userTwoId, Trade trade) {
    Optional<User> optionalUserGiving = userRepository.findById(userOneId);
    Optional<User> optionalUserReceiving = userRepository.findById(userTwoId);

    if (optionalUserGiving.isPresent() && optionalUserReceiving.isPresent()) {
      System.out.println("Giving: " + optionalUserGiving.get().getNome());

      // Here I get the inventory for each rebel
      Item rebelGivingInventory = optionalUserGiving.get().getInventory();
      Item rebelReceivingInventory = optionalUserReceiving.get().getInventory();

      // Here I retrieve the info sent via PATCH request
      Item giveItemsInventory = trade.getGive();
      Item receiveItemsInventory = trade.getReceive();

      // Validate the inputs
      validateItemInput(giveItemsInventory, rebelGivingInventory);
      validateItemInput(receiveItemsInventory, rebelReceivingInventory);

      int givePoints = calculatePoints(giveItemsInventory);
      int receivePoints = calculatePoints(receiveItemsInventory);

      System.out.println("Give points: " + givePoints);
      System.out.println("Receive points: " + receivePoints);


      if(givePoints == receivePoints) {
        System.out.println("É possível fazer a troca!");

        // Updates the rebel that is giving the items => currentItems - giving + receiving
        rebelGivingInventory.setWeaponQty(rebelGivingInventory.getWeaponQty() - giveItemsInventory.getWeaponQty() + receiveItemsInventory.getWeaponQty());
        rebelGivingInventory.setArmorQty(rebelGivingInventory.getArmorQty() - giveItemsInventory.getArmorQty() + receiveItemsInventory.getArmorQty());
        rebelGivingInventory.setWaterQty(rebelGivingInventory.getWaterQty() - giveItemsInventory.getWaterQty() + receiveItemsInventory.getWaterQty());
        rebelGivingInventory.setFoodQty(rebelGivingInventory.getFoodQty() - giveItemsInventory.getFoodQty() + receiveItemsInventory.getFoodQty());

        // Updates the rebel that is receiving the items => currentItems + giving - receiving
        rebelReceivingInventory.setWeaponQty(rebelReceivingInventory.getWeaponQty() + giveItemsInventory.getWeaponQty() - receiveItemsInventory.getWeaponQty());
        rebelReceivingInventory.setArmorQty(rebelReceivingInventory.getArmorQty() + giveItemsInventory.getArmorQty() - receiveItemsInventory.getArmorQty());
        rebelReceivingInventory.setWaterQty(rebelReceivingInventory.getWaterQty() + giveItemsInventory.getWaterQty() - receiveItemsInventory.getWaterQty());
        rebelReceivingInventory.setFoodQty(rebelReceivingInventory.getFoodQty() + giveItemsInventory.getFoodQty() - receiveItemsInventory.getFoodQty());
      }
      // Saves the first user
      userRepository.save(optionalUserGiving.get());

      // Saves the second user
      userRepository.save(optionalUserReceiving.get());

      System.out.println("Receiving: " + optionalUserReceiving.get().getNome());

      return "Operation successufully made!";
    }

    throw new UserNotFoundException();

  }

  public void validateItemInput(Item item, Item inventory) {
    if (item.getWeaponQty() > inventory.getWeaponQty() ||
        item.getArmorQty() > inventory.getArmorQty() ||
        item.getWaterQty() > inventory.getWaterQty() ||
        item.getFoodQty() > inventory.getFoodQty()
    ) {
      throw new InvalidTradeAmountException();
    }
  }


  public int calculatePoints(Item item) {
    return item.getWeaponQty() * 4 +
        item.getArmorQty() * 3 +
        item.getWaterQty() * 2 +
        item.getFoodQty();
  }

}
