package com.example.jpa_project.controller;

import com.example.jpa_project.entity.Task;
import com.example.jpa_project.payload.user.UserCreateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import com.example.jpa_project.payload.user.UserResponseDTO;
import com.example.jpa_project.service.serviceImpl.TaskServiceImpl;
import com.example.jpa_project.service.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private UserServiceImpl userService;
    private TaskServiceImpl taskService;

    @PostMapping("/admins")
    public ResponseEntity<UserCreateResponseDTO> createNewUser(@Valid @RequestBody UserCreateRequestDTO request) {
        UserCreateResponseDTO createdAdmin = userService.createUser(request);

        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<UserResponseDTO>> findAllAdmins() {
        List<UserResponseDTO> allAdmins = userService.findAllUsers();

        return new ResponseEntity<>(allAdmins, HttpStatus.OK);
    }

    @GetMapping("/admins/{email}")
    public ResponseEntity<UserResponseDTO> findUserByEmail(@PathVariable("email") String email) {
        UserResponseDTO adminByEmail = userService.findByUserEmail(email);

        return new ResponseEntity<>(adminByEmail, HttpStatus.OK);
    }

    @GetMapping("/admins/{username}")
    public ResponseEntity<UserResponseDTO> findUserByUsername(@PathVariable String username) {
        UserResponseDTO userByUsername = userService.findByUsername(username);

        return new ResponseEntity<>(userByUsername, HttpStatus.OK);
    }

    @DeleteMapping("/{admin_id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer admin_id){
        userService.deleteUser(admin_id);

        return new ResponseEntity<>("User with id " + admin_id + " was successfully deleted!", HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> findAllTasks() {
        List<Task> tasks = taskService.findAllForAdmin();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
