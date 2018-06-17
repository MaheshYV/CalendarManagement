package com.example.exception;
/**
 * @Author Mahesh
 *
 * @Version 1.0
 *
 */
public class CalendarEventNotFoundException extends Exception {
	private static final long serialVersionUID = 100L;

	public CalendarEventNotFoundException() {

	}

	public CalendarEventNotFoundException(String message) {
		super(message);
	}

	public CalendarEventNotFoundException(Throwable cause) {
		super(cause);
	}

	public CalendarEventNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}