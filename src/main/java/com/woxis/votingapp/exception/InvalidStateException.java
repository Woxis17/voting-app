package com.woxis.votingapp.exception;

public class InvalidStateException extends RuntimeException {

    private String description;

    public InvalidStateException(String description) {
        this.description = description;
    }
}
