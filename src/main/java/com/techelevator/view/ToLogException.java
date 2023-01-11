package com.techelevator.view;

public class ToLogException extends RuntimeException {

    public ToLogException(String message) {
        super(message);
    }

    public ToLogException(Throwable cause) {
        super(cause);
    }
}
