package com.example.jpa_project.controller;

import com.example.jpa_project.payload.task.TaskResponseDTO;
import com.example.jpa_project.service.serviceImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskServiceImpl service;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAllTasks() {
        List<TaskResponseDTO> tasks = service.findAllTasks();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findTaskById(@PathVariable Integer id) {
        TaskResponseDTO task = service.findTaskById(id);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

}
