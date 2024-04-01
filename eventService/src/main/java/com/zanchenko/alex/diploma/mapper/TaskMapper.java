package com.zanchenko.alex.diploma.mapper;

import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.dto.TaskDTO;

import java.util.List;

import static com.zanchenko.alex.diploma.mapper.EventMapper.mapTOEventForTask;
import static com.zanchenko.alex.diploma.mapper.EventMapper.mapToEventDTO;
import static com.zanchenko.alex.diploma.mapper.EventMapper.mapToEventDTOforTask;

public class TaskMapper {

    public static Task mapToTask(TaskDTO taskDTO){
        return Task.builder()
                .taskTitle(taskDTO.getTaskTitle())
                .taskDescription(taskDTO.getTaskDescription())
                .deadline(taskDTO.getDeadline())
                //.event(mapTOEventForTask(taskDTO.getEventDTO()))
                .build();
    }
    public static TaskDTO mapToTaskDTO(Task task){
        return TaskDTO.builder()
                .id(task.getId())
                .taskTitle(task.getTaskTitle())
                .deadline(task.getDeadline())
                .eventDTO(mapToEventDTOforTask(task.getEvent()))
                .taskDescription(task.getTaskDescription())
                .build();
    }
    public static List<TaskDTO> mapToTaskDTO(List<Task> tasks){
        return tasks.stream()
                .map(TaskMapper::mapToTaskDTO)
                .toList();
    }


}
