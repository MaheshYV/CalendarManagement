package com.example.exception;
/**
 * @Author Mahesh
 *
 * @Version 1.0
 *
 */
public class CalendarNotFoundException extends Exception {
	private static final long serialVersionUID = 100L;

	public CalendarNotFoundException() {

	}

	public CalendarNotFoundException(String message) {
		super(message);
	}

	public CalendarNotFoundException(Throwable cause) {
		super(cause);
	}

	public CalendarNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}