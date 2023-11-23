package com.example.jpa_project.service.serviceImpl;

import com.example.jpa_project.entity.Task;
import com.example.jpa_project.entity.TaskStatus;
import com.example.jpa_project.entity.User;
import com.example.jpa_project.exception.NotFoundException;
import com.example.jpa_project.payload.task.TaskCreateOrUpdateResponseDTO;
import com.example.jpa_project.payload.task.TaskCreateRequestDTO;
import com.example.jpa_project.payload.task.TaskResponseDTO;
import com.example.jpa_project.payload.task.TaskUpdateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import com.example.jpa_project.repository.TaskRepository;
import com.example.jpa_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    // for admin
    public List<Task> findAllForAdmin() {
        return taskRepository.findAll();
    }

    public List<TaskResponseDTO> findAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, TaskResponseDTO.class))
                .collect(Collectors.toList());
    }

    public TaskResponseDTO findTaskById(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task with " + id + " not exist"));

        return modelMapper.map(task, TaskResponseDTO.class);
    }

    public List<TaskResponseDTO> findTasksByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found with username: " + username));

        return taskRepository.findByUser(user).stream()
                .map(task -> modelMapper.map(task, TaskResponseDTO.class))
                .collect(Collectors.toList());
    }

    public TaskCreateOrUpdateResponseDTO createTask(TaskCreateRequestDTO requestDTO) {
        User foundUser = userRepository.findByUsername(requestDTO.getUsername()).orElseThrow(() -> new NotFoundException("User not found with username: " + requestDTO.getUsername()));
        Task newTask = modelMapper.map(requestDTO, Task.class);
        newTask.setUser(foundUser);
        newTask.setStatus(TaskStatus.Open);

        Task savedTask = taskRepository.save(newTask);

        TaskCreateOrUpdateResponseDTO taskMap = modelMapper.map(savedTask, TaskCreateOrUpdateResponseDTO.class);
        UserCreateResponseDTO userMap = modelMapper.map(foundUser, UserCreateResponseDTO.class);
        taskMap.setUserCreateResponseDTO(userMap);

        return taskMap;
    }

    public TaskCreateOrUpdateResponseDTO updateTask(TaskUpdateRequestDTO requestDTO) {
        Task existingTask = taskRepository.findById(requestDTO.getId()).orElseThrow(() -> new NotFoundException("Task with id: " + requestDTO.getId() + " not found"));

        if (existingTask.getUser().getUsername().equals(requestDTO.getUsername())) {
            taskRepository.save(existingTask);
            return modelMapper.map(existingTask, TaskCreateOrUpdateResponseDTO.class);
        } else {
            throw new NotFoundException("User: " + requestDTO.getUsername() + " can't update this task.");
        }
    }

    public void deleteTask(Integer id) {
        Task foundTask = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task with " + id + " not exist"));

        taskRepository.delete(foundTask);
    }

}





/*
public TaskCreateOrUpdateResponseDTO createTask(TaskCreateRequestDTO requestDTO) {
        User foundUser = userRepository.findByEmail(requestDTO.getEmail()).orElseThrow(() -> new NotFoundException("User not found with email: " + requestDTO.getEmail()));
        Task newTask = modelMapper.map(requestDTO, Task.class);
        newTask.setUser(foundUser);
        newTask.setStatus(TaskStatus.Open);

        Task savedTask = taskRepository.save(newTask);

        TaskCreateOrUpdateResponseDTO taskMap = modelMapper.map(savedTask, TaskCreateOrUpdateResponseDTO.class);
        UserCreateResponseDTO userMap = modelMapper.map(foundUser, UserCreateResponseDTO.class);
        taskMap.setUserCreateResponseDTO(userMap);

        return taskMap;
    }
 */













