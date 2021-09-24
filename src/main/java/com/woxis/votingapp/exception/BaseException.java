package com.woxis.votingapp.exception;

public class BaseException extends RuntimeException {

  private String description;

  public BaseException() {
    this.description = "Description unavailable";
  }

  public BaseException(String description) {
    this.description = description;
  }
}
