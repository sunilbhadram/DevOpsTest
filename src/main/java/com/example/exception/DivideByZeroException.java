package com.example.exception;

public class DivideByZeroException extends Exception {

	public DivideByZeroException() {
	}

	public DivideByZeroException(String message) {
		super(message);
	}

}
