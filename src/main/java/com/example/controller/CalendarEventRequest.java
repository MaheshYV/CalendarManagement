package com.example.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CalendarEventRequest {

	private Long calendarId;
	private String title;

	private LocalDate eventDate;

	private LocalTime eventTime;

	private String location;

	private List<String> attendeeList;

	private String reminderTime;

	public Long getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public LocalTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getAttendeeList() {
		return attendeeList;
	}

	public void setAttendeeList(List<String> attendeeList) {
		this.attendeeList = attendeeList;
	}

	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
}