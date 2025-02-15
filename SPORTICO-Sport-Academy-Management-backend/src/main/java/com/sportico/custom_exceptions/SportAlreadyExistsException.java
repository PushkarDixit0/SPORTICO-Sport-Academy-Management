package com.sportico.custom_exceptions;

public class SportAlreadyExistsException extends RuntimeException {
	public SportAlreadyExistsException(String message) {
        super(message);
    }
}
