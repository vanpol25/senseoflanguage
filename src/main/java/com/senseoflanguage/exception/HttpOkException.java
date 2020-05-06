package com.senseoflanguage.exception;

public class HttpOkException extends RuntimeException {

    public HttpOkException() {
        super();
    }

    public HttpOkException(String message) {
        super(message);
    }

    public HttpOkException(String message, Throwable cause) {
        super(message, cause);
    }

}
