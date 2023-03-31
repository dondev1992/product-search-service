package io.catalyte.training.sportsproducts.exceptions;

public class Conflict extends RuntimeException {

  public Conflict() {
  }

  public Conflict(String message) {
    super(message);
  }
}
