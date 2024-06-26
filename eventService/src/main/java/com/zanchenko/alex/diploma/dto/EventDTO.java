package com.zanchenko.alex.diploma.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zanchenko.alex.diploma.domain.Event;
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
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Title should not be empty")
    String eventTitle;
    @NotEmpty(message = "Photo URL should not be empty")
    String photoURL;
    @NotEmpty(message = "Description should not be empty")
    String eventDescription;
    @NotEmpty(message = "Location should not be empty")
    String eventLocation;
    FacilityDTO facilityDTO;
    LocalDate eventDate;
    Urgency urgency;
    EventType eventType;
    EventState eventState;
    LocalDate openEventDate;
    LocalDate closedEventDate;
    List<TaskDTO> tasks;
    List<UserDTO> executors;
}
