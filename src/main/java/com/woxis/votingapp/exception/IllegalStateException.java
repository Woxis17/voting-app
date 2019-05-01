package com.woxis.votingapp.exception;

public class IllegalStateException extends RuntimeException {

    private String description;

    public IllegalStateException(String description) {
        this.description = description;
    }
}
