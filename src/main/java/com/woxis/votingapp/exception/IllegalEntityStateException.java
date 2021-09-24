package com.woxis.votingapp.exception;

public class IllegalEntityStateException extends BaseException {

  public IllegalEntityStateException() {
  }

  public IllegalEntityStateException(String description) {
    super(description);
  }
}
