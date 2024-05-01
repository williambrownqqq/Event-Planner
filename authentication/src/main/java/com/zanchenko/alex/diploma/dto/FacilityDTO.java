package com.zanchenko.alex.diploma.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDTO {

    Long id;
    @NotEmpty(message = "Facility title should not be empty")
    @Size(min = 5, max = 100, message = "Facility title must be between 5 and 100 chars")
    String facilityTitle;
    @NotEmpty(message = "Photo URL should not be empty")
    String photoURL;
    @NotEmpty(message = "Description should not be empty")
    @Size(min = 5, max = 100, message = "Description must be more than 5")
    String description;
    Set<EventDTO> events;

}
