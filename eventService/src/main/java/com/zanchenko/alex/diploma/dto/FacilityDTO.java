package com.zanchenko.alex.diploma.dto;

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
    String facilityTitle;
    String photoURL;
    String description;
    Set<EventDTO> events;

}
