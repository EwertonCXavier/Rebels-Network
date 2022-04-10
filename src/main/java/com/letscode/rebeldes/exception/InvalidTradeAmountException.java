package com.letscode.rebeldes.exception;

public class InvalidTradeAmountException extends RuntimeException{
  public InvalidTradeAmountException() {
    super("Items amount provided are greater than registered for the respective user!");
  }
}
