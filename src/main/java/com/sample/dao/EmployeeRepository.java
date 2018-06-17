package com.example.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.CalendarEvent;

public interface CalendarEventRepository extends JpaRepository<CalendarEvent, Long> {
	Optional<CalendarEvent> findById(Long id);

	@Query("SELECT e FROM CalendarEvent e WHERE e.eventDate = :eventDate")
	List<CalendarEvent> findByEventDate(@Param("eventDate") LocalDate eventDate);

	@Query("SELECT e FROM CalendarEvent e WHERE e.eventDate between :startDate and :endDate")
	List<CalendarEvent> findEventsByStartAndEndDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
} // end CalendarEventRepository interface