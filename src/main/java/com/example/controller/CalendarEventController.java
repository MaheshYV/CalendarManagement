package com.example.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.CalendarEvent;
import com.example.exception.CalendarEventCreateException;
import com.example.exception.CalendarEventException;
import com.example.exception.CalendarEventNotFoundException;
import com.example.exception.CalendarNotFoundException;
import com.example.service.CalendarEventService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@RestController
@RequestMapping("/calendar/events")
public class CalendarEventController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CalendarEventService calendarEventService;

	/***
	 * Returns all the calendar events for the user
	 * 
	 * @return
	 */
	@GetMapping
	public List<CalendarEvent> getAllEvents() {
		return calendarEventService.getAllEvents();
	}

	/***
	 * Returns the calendar events for the specified date
	 * 
	 * @return
	 * @throws CalendarEventException
	 */
	@GetMapping("/day/{eventDate}")
	public ResponseEntity<?> getDayEvents(
			@PathVariable(name = "eventDate", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate eventDate)
			throws CalendarEventException {

		logger.info("[CalendarEventController:getDayEvents] Start of the method");
		logger.info("[CalendarEventController:getDayEvents] eventDate = " + eventDate);

		ResponseEntity<?> responseEntity = null;

		List<CalendarEvent> calendarEventList = new ArrayList<>();
		try {
			calendarEventList = calendarEventService.getDayEvents(eventDate);
			if (calendarEventList != null && !calendarEventList.isEmpty()) {
				responseEntity = new ResponseEntity<>(calendarEventList, HttpStatus.OK);
			} else {
				String message = "No events found for the date " + eventDate;
				responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch (CalendarEventCreateException e) {
			String errorMessage = "Error retrieving the events for the day, exception " + e;
			responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("[CalendarEventController:getDayEvents] End of the method");

		return responseEntity;
	}

	/***
	 * Returns the calendar events for the specified year and month
	 * 
	 * @return
	 * @throws CalendarEventException
	 */
	@GetMapping("/month/{year}/{month}")
	public ResponseEntity<?> getMonthEvents(@PathVariable(name = "year", required = true) Integer year,
			@PathVariable(name = "month", required = true) Integer month) throws CalendarEventException {

		logger.info("[CalendarEventController:getMonthEventss] Start of the method");
		logger.info("[CalendarEventController:getMonthEvents] month = " + month);
		logger.info("[CalendarEventController:getMonthEvents] year = " + year);

		ResponseEntity<?> responseEntity = null;

		List<CalendarEvent> calendarEventList = new ArrayList<>();
		try {
			calendarEventList = calendarEventService.getMonthEvents(year, month);
			if (calendarEventList != null && !calendarEventList.isEmpty()) {
				responseEntity = new ResponseEntity<>(calendarEventList, HttpStatus.OK);
			} else {
				String monthName = LocalDate.of(year, month, 1).getMonth().name();
				String message = "No events found for the month " + monthName + " and year = " + year;
				responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch (CalendarEventCreateException e) {
			String errorMessage = "Error retrieving the events for the month and year, exception " + e;
			responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("[CalendarEventController:getMonthEvents] End of the method");

		return responseEntity;
	}

	/***
	 * Returns the calendar events for the specified year and month
	 * 
	 * @return
	 * @throws CalendarEventException
	 */
	@GetMapping("/week/{fromDate}/{toDate}")
	public ResponseEntity<?> getEventsByWeek(
			@PathVariable(name = "fromDate", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate fromDate,
			@PathVariable(name = "toDate", required = true) @DateTimeFormat(iso = ISO.DATE) LocalDate toDate)
			throws CalendarEventException {

		logger.info("[CalendarEventController:getEventsByWeek] Start of the method");
		logger.info("[CalendarEventController:getEventsByWeek] fromDate = " + fromDate);
		logger.info("[CalendarEventController:getEventsByWeek] toDate = " + toDate);

		ResponseEntity<?> responseEntity = null;

		List<CalendarEvent> calendarEventList = new ArrayList<>();
		try {
			calendarEventList = calendarEventService.getEventsByWeek(fromDate, toDate);
			if (calendarEventList != null && !calendarEventList.isEmpty()) {
				responseEntity = new ResponseEntity<>(calendarEventList, HttpStatus.OK);
			} else {
				String message = "No events found for the dates between " + fromDate + " to " + toDate;
				responseEntity = new ResponseEntity<>(message, HttpStatus.OK);
			}
		} catch (CalendarEventCreateException e) {
			String errorMessage = "Error retrieving the events for the month and year, exception " + e;
			logger.error(errorMessage);
			responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.info("[CalendarEventController:getMonthEvents] End of the method");

		return responseEntity;
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public ResponseEntity<?> createEvent(@RequestBody CalendarEventRequest calendarEventRequest) {

		ResponseEntity<?> responseEntity = null;

		try {
			calendarEventService.createEvent(calendarEventRequest);
			responseEntity = new ResponseEntity<>("Event " + calendarEventRequest.getTitle() + " succesfuly created",
					HttpStatus.CREATED);
		} catch (CalendarEventCreateException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	} // end createEvent method

	@PutMapping(path = "/update/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public ResponseEntity<?> updateEvent(@PathVariable("eventId") Long eventId,
			@RequestBody CalendarEventRequest calendarEventRequest) {

		ResponseEntity<?> responseEntity = null;

		try {
			calendarEventService.updateEvent(eventId, calendarEventRequest);
			responseEntity = new ResponseEntity<>("Event " + calendarEventRequest.getTitle() + " succesfuly updated",
					HttpStatus.OK);
		} catch (CalendarEventCreateException e) {
			logger.error(e.getMessage());
		} catch (CalendarEventNotFoundException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		} catch (CalendarNotFoundException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		} catch (CalendarEventException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	} // end updateEvent method

	@DeleteMapping("/delete/{eventId}")
	public ResponseEntity<?> deleteEvent(@PathVariable("eventId") Long eventId) {

		ResponseEntity<?> responseEntity = null;

		try {
			calendarEventService.deleteEvent(eventId);
			responseEntity = new ResponseEntity<>("Event " + eventId + " succesfuly deleted", HttpStatus.NO_CONTENT);
		} catch (CalendarEventException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	}

} // end