package com.letscode.rebeldes.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(){
    super("User not found!");
  }
}
