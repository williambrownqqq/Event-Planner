package com.zanchenko.alex.diploma.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * The {@code Task} class represents a task associated with an event in the database.
 * Extends {@link BaseEntity} class, providing a common structure for all entity classes.
 * The class defines properties specific to a task.
 *
 * <p>
 * This entity class is mapped to the "task" table in the database.
 * </p>
 *
 * @author Alex Zanchenko
 */

@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task extends BaseEntity{

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
