package com.zanchenko.alex.diploma.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zanchenko.alex.diploma.domain.enumeration.EventState;
import com.zanchenko.alex.diploma.domain.enumeration.EventType;
import com.zanchenko.alex.diploma.domain.enumeration.Urgency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
import java.util.ArrayList;
import java.util.List;

/***
 * The {@code Event} class represents an event in a concrete energy system facility in the database.
 *  Extends {@link BaseEntity} class, providing a common structure for all entity classes.
 *  The class defines properties specific to a event.
 *
 *  @author Alex Zanchenko
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "event")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event extends BaseEntity {

    @Column(name = "event_title")
    String eventTitle;

    @Column(name = "photo_url", length = 500)
    String photoURL;

    @Column(name = "event_description", length = 5000)
    String eventDescription;

    @Column(name = "event_location")
    String eventLocation;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "facility_id")
    Facility facility;

    @Column(name = "event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate eventDate;

    @Column(name = "urgency")
    @Enumerated(EnumType.STRING)
    Urgency urgency;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    EventType eventType;

    @Column(name = "event_state")
    @Enumerated(EnumType.STRING)
    EventState eventState;

    @Column(name = "open_event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDate openEventDate;

    @Column(name = "closed_event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDate closedEventDate;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    List<Task> tasks = new ArrayList<>();
}
