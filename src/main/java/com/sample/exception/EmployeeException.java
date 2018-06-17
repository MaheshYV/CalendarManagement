package com.example.exception;
/**
 * @Author Mahesh
 *
 * @Version 1.0
 *
 */
public class CalendarEventException extends Exception {
	private static final long serialVersionUID = 100L;

	public CalendarEventException() {

	}

	public CalendarEventException(String message) {
		super(message);
	}

	public CalendarEventException(Throwable cause) {
		super(cause);
	}

	public CalendarEventException(String message, Throwable cause) {
		super(message, cause);
	}
}
