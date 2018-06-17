package com.example.dao; 

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}