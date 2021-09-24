package com.woxis.votingapp.exception;

public class AlreadyExistsException extends BaseException {

  public AlreadyExistsException() {
  }

  public AlreadyExistsException(String description) {
    super(description);
  }
}
