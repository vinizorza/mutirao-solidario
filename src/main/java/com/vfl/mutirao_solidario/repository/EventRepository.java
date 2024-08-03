package com.vfl.mutirao_solidario.repository;

import com.vfl.mutirao_solidario.enums.Status;
import com.vfl.mutirao_solidario.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long>{

    @Query("select e from Event e where " +
            "e.organizer.id = ?1 or ?1 is null and " +
            "e.date > ?2 or cast(?2 as date) is null and " +
            "e.date < ?3 or cast(?3 as date) is null and " +
            "e.status = ?4 or ?4 is null")
    List<Event> findEvents(Long organizerId, LocalDateTime dateFrom, LocalDateTime dateTo, List<Status> status);

}
