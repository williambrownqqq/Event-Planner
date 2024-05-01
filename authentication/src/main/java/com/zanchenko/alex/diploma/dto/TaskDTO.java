package com.zanchenko.alex.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    Long id;
    String taskTitle;
    String taskDescription;
    LocalDate deadline;
    EventDTO eventDTO;
}
