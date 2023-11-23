package com.example.jpa_project.controller;

import com.example.jpa_project.payload.task.TaskCreateOrUpdateResponseDTO;
import com.example.jpa_project.payload.task.TaskCreateRequestDTO;
import com.example.jpa_project.payload.task.TaskResponseDTO;
import com.example.jpa_project.payload.task.TaskUpdateRequestDTO;
import com.example.jpa_project.service.serviceImpl.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskServiceImpl service;

    public TaskController(TaskServiceImpl toDoService) {
        this.service = toDoService;
    }

    @PostMapping
    public ResponseEntity<TaskCreateOrUpdateResponseDTO> addTodo(@RequestBody TaskCreateRequestDTO todoDto) {
        TaskCreateOrUpdateResponseDTO toDo = service.createTask(todoDto);

        return new ResponseEntity<>(toDo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAllTodos() {
        List<TaskResponseDTO> todos = service.findAllTasks();

        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findToDoById(@PathVariable Integer id) {
        TaskResponseDTO todo = service.findTaskById(id);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TaskCreateOrUpdateResponseDTO> updateTodo(@RequestBody TaskUpdateRequestDTO todoDto) {
        TaskCreateOrUpdateResponseDTO todo = service.updateTask(todoDto);

        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id) {
        service.deleteTask(id);

        return new ResponseEntity<>("User with id " + id + " was successfully deleted!", HttpStatus.OK);

    }
}
