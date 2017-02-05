package com.stolser.search;

public class TimeoutSearchException extends RuntimeException {
    public TimeoutSearchException(String message) {
        super(message);
    }

    public TimeoutSearchException(String message, Throwable cause) {
        super(message, cause);
    }
}
