package com.zanchenko.alex.diploma.domain.network;

import com.zanchenko.alex.diploma.dto.FacilityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacilityResponse extends Response{
    private FacilityDTO facilityDTO;
}
