package com.stolser.search;

public class GettingFutureResultsException extends RuntimeException {
    public GettingFutureResultsException(String message) {
        super(message);
    }

    public GettingFutureResultsException(String message, Throwable cause) {
        super(message, cause);
    }
}
