package com.example.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.controller.CalendarEventRequest;
import com.example.dao.CalendarEventRepository;
import com.example.dao.CalendarRepository;
import com.example.domain.Calendar;
import com.example.domain.CalendarEvent;
import com.example.exception.CalendarEventCreateException;
import com.example.exception.CalendarEventException;
import com.example.exception.CalendarEventNotFoundException;
import com.example.exception.CalendarNotFoundException;

@Service
public class CalendarEventService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CalendarRepository calendarRepository;

	@Autowired
	private CalendarEventRepository calendarEventRepository;

	public List<CalendarEvent> getAllEvents() {
		return calendarEventRepository.findAll();
	} // end getAllEvents method

	public List<CalendarEvent> getDayEvents(LocalDate eventDate) throws CalendarEventException {

		logger.info("[CalendarEventService:getDayEvents] Start of the method");
		logger.debug("[CalendarEventService:getDayEvents] debug Start of the method");

		logger.info("[CalendarEventService:getDayEvents] eventDate = " + eventDate);

		List<CalendarEvent> calendarEventList = new ArrayList<>();

		try {
			calendarEventList = calendarEventRepository.findByEventDate(eventDate);
		} catch (Exception e) {
			String errorMessage = "Error retrieving the Calendar Events for the day " + "'" + eventDate + "'";
			logger.error(errorMessage);
			throw new CalendarEventException(errorMessage);
		}
		
		logger.info("[CalendarEventService:getDayEvents] calendarEventList = " + calendarEventList);
		logger.info("[CalendarEventService:getDayEvents] End of the method");

		return calendarEventList;

	} // end getDayEvents method

	public List<CalendarEvent> getMonthEvents(Integer year, Integer month) throws CalendarEventException {

		logger.info("[CalendarEventService:getMonthEvents] Start of the method");

		LocalDate startDate = getStartDate(year, month);
		LocalDate endDate = getEndDate(year, month);

		logger.info("[CalendarEventService:getMonthEvents] startDate = " + startDate);
		logger.info("[CalendarEventService:getMonthEvents] endDate = " + endDate);

		List<CalendarEvent> calendarEventList = new ArrayList<>();

		try {
			calendarEventList = calendarEventRepository.findEventsByStartAndEndDate(startDate, endDate);
		} catch (Exception e) {
			String errorMessage = "Error retrieving the Calendar Events for the start date  " + "'" + startDate + "'" + " and end date " + "'" + endDate + "'";
			logger.error(errorMessage);
			throw new CalendarEventException(errorMessage);
		}
		
		logger.info("[CalendarEventService:getDayEvents] calendarEventList = " + calendarEventList);
		logger.info("[CalendarEventService:getMonthEvents] End of the method");
		
		return calendarEventList;
	}

	public List<CalendarEvent> getEventsByWeek(LocalDate fromDate, LocalDate toDate) throws CalendarEventException {
		logger.info("[CalendarEventService:getEventsByWeek] Start of the method");

		logger.info("[CalendarEventService:getEventsByWeek] fromDate = " + fromDate);
		logger.info("[CalendarEventService:getEventsByWeek] toDate = " + toDate);

		List<CalendarEvent> calendarEventList = new ArrayList<>();

		try {
			calendarEventList = calendarEventRepository.findEventsByStartAndEndDate(fromDate, toDate);
		} catch (Exception e) {
			String errorMessage = "Error retrieving the Calendar Events for the dates  between " + "'" + fromDate + "'" + " and to date " + "'" + toDate + "'";
			logger.error(errorMessage);
			throw new CalendarEventException(errorMessage);
		}
		logger.info("[CalendarEventService:getEventsByWeek] calendarEventList = " + calendarEventList);

		logger.info("[CalendarEventService:getEventsByWeek] End of the method");
		
		return calendarEventList;
	}

	private LocalDate getStartDate(Integer year, Integer month) {
		return LocalDate.of(year, month, 1);
	}

	private LocalDate getEndDate(Integer year, Integer month) {
		LocalDate endDate = YearMonth.of(year, month).atEndOfMonth();
		return endDate;
	}

	@Transactional
	public void createEvent(@RequestBody CalendarEventRequest calendarEventRequest)
			throws CalendarEventCreateException {

		Calendar calendar = calendarRepository.findOne(calendarEventRequest.getCalendarId());

		CalendarEvent calendarEvent = new CalendarEvent();

		calendarEvent.setTitle(calendarEventRequest.getTitle());
		calendarEvent.setEventDate(calendarEventRequest.getEventDate());
		calendarEvent.setEventTime(calendarEventRequest.getEventTime());
		String attendeeList = String.join(", ", calendarEventRequest.getAttendeeList());
		calendarEvent.setAttendeeList(attendeeList);
		calendarEvent.setLocation(calendarEventRequest.getLocation());
		calendarEvent.setReminderTime(calendarEventRequest.getReminderTime());

		calendarEvent.setCalendar(calendar);

		calendarEventRepository.save(calendarEvent);

	}

	public void updateEvent(Long calendarEventId, CalendarEventRequest calendarEventRequest)
			throws CalendarEventNotFoundException, CalendarNotFoundException, CalendarEventException {

		CalendarEvent calendarEvent = null;
		Calendar calendar = null;

		try {
			calendarEvent = calendarEventRepository.findOne(calendarEventId);

			calendarEvent.setTitle(calendarEventRequest.getTitle());
			calendarEvent.setEventDate(calendarEventRequest.getEventDate());
			calendarEvent.setEventTime(calendarEventRequest.getEventTime());
			String attendeeList = String.join(", ", calendarEventRequest.getAttendeeList());
			calendarEvent.setAttendeeList(attendeeList);
			calendarEvent.setLocation(calendarEventRequest.getLocation());
			calendarEvent.setReminderTime(calendarEventRequest.getReminderTime());

		} catch (Exception e) {
			String errorMessage = "Calendar Event " + "'" + calendarEventId + "'" + " Not Found";
			throw new CalendarEventNotFoundException(errorMessage);
		}

		try {
			calendar = calendarRepository.findOne(calendarEventRequest.getCalendarId());
			calendarEvent.setCalendar(calendar);
		} catch (Exception e) {
			String errorMessage = "Calendar " + "'" + calendarEventRequest.getCalendarId() + "'" + " Not Found";
			throw new CalendarNotFoundException(errorMessage);
		}

		calendarEventRepository.save(calendarEvent);

		// return
		// calendarEventRepository.findById(calendarEventId).map(calendarEvent
		// -> {
		//
		// Calendar calendar =
		// calendarRepository.findOne(calendarEventRequest.getCalendarId());
		//
		// calendarEvent.setTitle(calendarEventRequest.getTitle());
		// calendarEvent.setEventDate(calendarEventRequest.getEventDate());
		// calendarEvent.setEventTime(calendarEventRequest.getEventTime());
		// String attendeeList = String.join(", ",
		// calendarEventRequest.getAttendeeList());
		// calendarEvent.setAttendeeList(attendeeList);
		// calendarEvent.setLocation(calendarEventRequest.getLocation());
		// calendarEvent.setReminderTime(calendarEventRequest.getReminderTime());
		//
		// calendarEvent.setCalendar(calendar);
		//
		// calendarEventRepository.save(calendarEvent);
		//
		// }).orElseThrow(() -> new
		// CalenderEventNotFoundException(calendarEventId));
	}

	public void deleteEvent(Long calendarEventId) throws CalendarEventException {

		try {
			calendarEventRepository.delete(calendarEventId);
		} catch (Exception e) {
			String errorMessage = "Calendar Event " + "'" + calendarEventId + "'" + " can not be deleted";
			throw new CalendarEventException(errorMessage);
		}

	}

}