package com.zanchenko.alex.diploma.service.impl;

import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.dto.TaskDTO;
import com.zanchenko.alex.diploma.exception.BadRequestException;
import com.zanchenko.alex.diploma.repository.TaskRepository;
import com.zanchenko.alex.diploma.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.zanchenko.alex.diploma.mapper.TaskMapper.mapToTask;
import static com.zanchenko.alex.diploma.mapper.TaskMapper.mapToTaskDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    @Override
    public List<TaskDTO> getAllTasks() {
        return mapToTaskDTO(taskRepository.findAll());
    }

    @Override
    public TaskDTO saveTask(TaskDTO taskDTO) {
        taskRepository.save(mapToTask(taskDTO));
        return taskDTO;
    }

    @Override
    public TaskDTO getTaskByID(Long taskID) {
        Task task =  taskRepository.findById(taskID)
                .orElseThrow(() -> new EntityNotFoundException("No task with ID: " + taskID));
        return mapToTaskDTO(task);
    }

    @Override
    public void updateTask(Long taskID, TaskDTO taskDTO) {
        if(taskRepository.existsById(taskID)){
            throw new BadRequestException("No task with ID:" + taskID);
        }
        Task task = mapToTask(taskDTO);
        task.setId(taskDTO.getId());
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskID) {
        taskRepository.deleteById(taskID);
    }
}
