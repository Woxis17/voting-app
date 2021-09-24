package com.woxis.votingapp.exception;

public class AlreadyPerformedException extends BaseException {

  public AlreadyPerformedException() {
  }

  public AlreadyPerformedException(String description) {
    super(description);
  }
}
