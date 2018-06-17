package com.example.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Mahesh
 *
 * @Version 1.0
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CalendarEventCreateException extends RuntimeException {

	public CalendarEventCreateException(String title) {
		super(title + " event could not created");
	}
}
