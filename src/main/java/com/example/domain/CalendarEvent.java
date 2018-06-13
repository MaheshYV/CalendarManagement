package com.example.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class CalendarEvent {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private LocalDate eventDate;
	
	private LocalTime eventTime;
	
//	@OneToOne
//	private Location location;
//	
//	@OneToMany
//	private List<Attendee> attendeeList;
	
	private String location;
	
	private String attendeeList;
	
	private String reminderTime;
	
	private Boolean blnReminderSent;
	
	@ManyToOne
	@JsonIgnore
	private Calendar calendar;
	
	@JsonProperty("calendar")
	public Long getCalenderId() {
		return calendar.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAttendeeList() {
		return attendeeList;
	}

	public void setAttendeeList(String attendeeList) {
		this.attendeeList = attendeeList;
	}

	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

	public Boolean getBlnReminderSent() {
		return blnReminderSent;
	}

	public void setBlnReminderSent(Boolean blnReminderSent) {
		this.blnReminderSent = blnReminderSent;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	@Override
	public String toString() {
		return "CalendarEvent [id=" + id + ", title=" + title + ", eventDate=" + eventDate + ", eventTime=" + eventTime
				+ ", location=" + location + ", attendeeList=" + attendeeList + ", reminderTime=" + reminderTime
				+ ", blnReminderSent=" + blnReminderSent + ", calendar=" + calendar + "]";
	} // end toString method
	
} // end CalendarEvent class