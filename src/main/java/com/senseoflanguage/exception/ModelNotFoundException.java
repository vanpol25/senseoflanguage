package com.senseoflanguage.exception;

public class ModelNotFoundException extends RuntimeException {

        public ModelNotFoundException() {
            super();
        }

        public ModelNotFoundException(String message) {
            super(message);
        }

        public ModelNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

}
