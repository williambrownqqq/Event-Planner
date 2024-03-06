package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTasks();

    TaskDTO saveTask(TaskDTO taskDTO);

    TaskDTO getTaskByID(Long TaskID);

    void updateTask(Long taskID, TaskDTO taskDTO);

    void deleteTask(Long taskID);

}
