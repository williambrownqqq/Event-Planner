package com.zanchenko.alex.diploma.domain.network;


import com.zanchenko.alex.diploma.dto.EventDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventResponse extends Response{
    private EventDTO eventDTO;
}
