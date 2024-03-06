package com.zanchenko.alex.diploma.repository;

import com.zanchenko.alex.diploma.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Query("DELETE FROM Task t WHERE t.event.id = :eventId")
    void deleteByEventId(@Param("eventId") Long eventId);

}
