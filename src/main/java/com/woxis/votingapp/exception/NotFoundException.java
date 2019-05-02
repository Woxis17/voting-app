package com.woxis.votingapp.exception;

public class NotFoundException extends BaseException {

    public NotFoundException() {
    }

    public NotFoundException(String description) {
        super(description);
    }
}
