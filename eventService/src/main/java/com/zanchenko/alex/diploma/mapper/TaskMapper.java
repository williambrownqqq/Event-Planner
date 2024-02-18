package com.zanchenko.alex.diploma.mapper;

import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.dto.TaskDTO;

import static com.zanchenko.alex.diploma.mapper.EventMapper.mapTOEventForTask;

public class TaskMapper {

    public static Task mapToTask(TaskDTO taskDTO){
        return Task.builder()
                .taskTitle(taskDTO.getTaskTitle())
                .taskDescription(taskDTO.getTaskDescription())
                .deadline(taskDTO.getDeadline())
                .event(mapTOEventForTask(taskDTO.getEventDTO()))
                .build();
    }
}
