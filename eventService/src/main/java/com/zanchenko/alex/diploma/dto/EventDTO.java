package com.zanchenko.alex.diploma.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zanchenko.alex.diploma.domain.Facility;
import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.domain.enumeration.EventState;
import com.zanchenko.alex.diploma.domain.enumeration.EventType;
import com.zanchenko.alex.diploma.domain.enumeration.Urgency;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    Long id;
    String eventTitle;
    String photoURL;
    String eventDescription;
    String eventLocation;
    FacilityDTO facilityDTO;
    LocalDate eventDate;
    Urgency urgency;
    EventType eventType;
    EventState eventState;
    LocalDate openEventDate;
    LocalDate closedEventDate;
    List<TaskDTO> tasks;
}
