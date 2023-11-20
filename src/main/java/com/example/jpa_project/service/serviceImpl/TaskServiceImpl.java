package com.example.jpa_project.service.serviceImpl;

import com.example.jpa_project.entity.Task;
import com.example.jpa_project.exception.NotFoundException;
import com.example.jpa_project.payload.task.TaskCreateOrUpdateResponseDTO;
import com.example.jpa_project.payload.task.TaskCreateRequestDTO;
import com.example.jpa_project.payload.task.TaskResponseDTO;
import com.example.jpa_project.payload.task.TaskUpdateRequestDTO;
import com.example.jpa_project.repository.TaskRepository;
import com.example.jpa_project.utils.TaskConverters;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl {

    private TaskRepository repository;
    private TaskConverters converters;

    public List<TaskResponseDTO> findAllTasks() {
        List<Task> tasks = repository.findAll();

        return tasks.stream()
                .map(converters::toDto)
                .collect(Collectors.toList());
    }

    public TaskResponseDTO findTaskById(Integer id) {
        Optional<Task> task = repository.findById(id);

        return converters.toDto(task.orElseThrow(() -> new NotFoundException("Task with " + id + " not exist")));
    }

    public TaskCreateOrUpdateResponseDTO createTask(TaskCreateRequestDTO requestDTO) {
        Task newTask = converters.fromCreateRequest(requestDTO);
        newTask = repository.save(newTask);

        return converters.toCreateDto(newTask);
    }

    public TaskCreateOrUpdateResponseDTO updateTask(TaskUpdateRequestDTO requestDTO) {
        Task foundTask = converters.fromUpdateRequest(requestDTO);
        foundTask = repository.save(foundTask);

        return converters.toCreateDto(foundTask);
    }

    public void deleteTask(Integer id) {
        Task foundTask = repository.findById(id).orElseThrow(() -> new NotFoundException("Task with " + id + " not exist"));

        repository.delete(foundTask);
    }

}



















