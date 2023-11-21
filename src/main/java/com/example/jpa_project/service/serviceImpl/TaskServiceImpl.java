package com.example.jpa_project.service.serviceImpl;

import com.example.jpa_project.entity.Task;
import com.example.jpa_project.exception.NotFoundException;
import com.example.jpa_project.payload.task.TaskCreateOrUpdateResponseDTO;
import com.example.jpa_project.payload.task.TaskCreateRequestDTO;
import com.example.jpa_project.payload.task.TaskResponseDTO;
import com.example.jpa_project.payload.task.TaskUpdateRequestDTO;
import com.example.jpa_project.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl {

    private TaskRepository repository;
    private ModelMapper modelMapper;

    public List<TaskResponseDTO> findAllTasks() {
        List<Task> tasks = repository.findAll();

        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskResponseDTO.class))
                .collect(Collectors.toList());
    }

    public TaskResponseDTO findTaskById(Integer id) {
        Optional<Task> task = repository.findById(id);

        return modelMapper.map(task.orElseThrow(() -> new NotFoundException("Task with " + id + " not exist")), TaskResponseDTO.class);
    }

    public TaskCreateOrUpdateResponseDTO createTask(TaskCreateRequestDTO requestDTO) {
        Task newTask = modelMapper.map(requestDTO, Task.class);
        newTask = repository.save(newTask);

        return modelMapper.map(newTask, TaskCreateOrUpdateResponseDTO.class);
    }

    public TaskCreateOrUpdateResponseDTO updateTask(TaskUpdateRequestDTO requestDTO) {
        Task foundTask = modelMapper.map(requestDTO, Task.class);
        foundTask = repository.save(foundTask);

        return modelMapper.map(foundTask, TaskCreateOrUpdateResponseDTO.class);
    }

    public void deleteTask(Integer id) {
        Task foundTask = repository.findById(id).orElseThrow(() -> new NotFoundException("Task with " + id + " not exist"));

        repository.delete(foundTask);
    }

}



















