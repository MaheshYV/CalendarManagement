package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.CalendarEventRepository;
import com.example.dao.CalendarRepository;
import com.example.domain.Calendar;

@RestController
public class CalendarController {
	
    @Autowired
    private CalendarEventRepository calendarEventRepository;
	
    @Autowired
    private CalendarRepository calendarRepository;
	
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Welcome!";
    }

    /***
     * Returns all the available calendars
     * 
     * @return
     */
    @GetMapping("/api/calendar")
    public List<Calendar> getCalendar() {
    	return calendarRepository.findAll();    	
    }
    
//    @GetMapping("/api/events")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    Iterable<CalendarEvent> getEvents(@RequestParam("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from, @RequestParam("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime to) {
//    	return calendarEventRepository.findBetween(from, to);    	
//    }
    
   //  @PostMapping("/api/events/create")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
 //   @Transactional
//    CalendarEvent createEvent(@RequestBody CalendarEventRequest params) {
//    	
//        Calendar calendar = calendarRepository.findOne(params.resource);   	
//    	
//        CalendarEvent calendarEvent = new CalendarEvent();
////        calendarEvent.setStart(params.start);
////        calendarEvent.setEnd(params.end);
////        calendarEvent.setText(params.text);
//        calendarEvent.setCalendar(calendar);
//    	
//        calendarEventRepository.save(calendarEvent);
//    	
//    	return calendarEvent;
//    }


}