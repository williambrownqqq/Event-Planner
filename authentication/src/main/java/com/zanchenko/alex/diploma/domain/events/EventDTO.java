package com.zanchenko.alex.diploma.domain.events;


import com.zanchenko.alex.diploma.domain.events.enumeration.EventState;
import com.zanchenko.alex.diploma.domain.events.enumeration.EventType;
import com.zanchenko.alex.diploma.domain.events.enumeration.Urgency;
import com.zanchenko.alex.diploma.dto.FacilityDTO;
import com.zanchenko.alex.diploma.dto.TaskDTO;
import com.zanchenko.alex.diploma.dto.UserDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
