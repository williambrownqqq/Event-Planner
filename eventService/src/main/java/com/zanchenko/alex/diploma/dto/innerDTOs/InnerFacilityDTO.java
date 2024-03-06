package com.zanchenko.alex.diploma.dto.innerDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InnerFacilityDTO {
    Long id;
    String facilityTitle;
}
