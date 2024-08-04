package com.bookstore.bookstore.exception;

public class AlreadyPresentException extends RuntimeException{
    public AlreadyPresentException() {
        super();
    }

    public AlreadyPresentException(String message) {
        super(message);
    }

    public AlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyPresentException(Throwable cause) {
        super(cause);
    }

    protected AlreadyPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
