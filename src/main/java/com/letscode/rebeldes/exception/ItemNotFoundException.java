package com.letscode.rebeldes.exception;

public class ItemNotFoundException extends RuntimeException{
  public ItemNotFoundException() {
    super("Item not found!");
  }
}
