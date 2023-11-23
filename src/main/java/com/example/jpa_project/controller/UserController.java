package com.example.jpa_project.controller;

import com.example.jpa_project.payload.task.TaskCreateOrUpdateResponseDTO;
import com.example.jpa_project.payload.task.TaskCreateRequestDTO;
import com.example.jpa_project.payload.task.TaskResponseDTO;
import com.example.jpa_project.payload.task.TaskUpdateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import com.example.jpa_project.payload.user.UserResponseDTO;
import com.example.jpa_project.service.serviceImpl.TaskServiceImpl;
import com.example.jpa_project.service.serviceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/task")
@AllArgsConstructor
public class UserController {

    private TaskServiceImpl taskService;

    @GetMapping(params = "username")
    public ResponseEntity<List<TaskResponseDTO>> findTaskByUsername(@RequestParam String username) {
        List<TaskResponseDTO> tasksByUsername = taskService.findTasksByUsername(username);

        return new ResponseEntity<>(tasksByUsername, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskCreateOrUpdateResponseDTO> createNewTask(@RequestBody TaskCreateRequestDTO request) {
        TaskCreateOrUpdateResponseDTO createdTask = taskService.createTask(request);

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TaskCreateOrUpdateResponseDTO> updateTaskById(@RequestBody TaskUpdateRequestDTO request) {
        TaskCreateOrUpdateResponseDTO updatedTask = taskService.updateTask(request);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Integer id) {
        taskService.deleteTask(id);

        return new ResponseEntity<>("User with id " + id + " was deleted!", HttpStatus.OK);
    }

}
