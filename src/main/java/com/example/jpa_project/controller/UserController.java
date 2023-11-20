package com.example.jpa_project.controller;

import com.example.jpa_project.payload.user.UserCreateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import com.example.jpa_project.payload.user.UserResponseDTO;
import com.example.jpa_project.service.serviceImpl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserServiceImpl service;

    public UserController(UserServiceImpl userService) {
        this.service = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserCreateResponseDTO> addUser(@RequestBody UserCreateRequestDTO userDto) {
        UserCreateResponseDTO createdUser = service.createUser(userDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        List<UserResponseDTO> users = service.findAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<UserResponseDTO> findUserByEmail(@RequestParam String email) {
        UserResponseDTO foundUser = service.findByUserEmail(email);

        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        service.deleteUser(id);

        return new ResponseEntity<>("User with id " + id + " was deleted!", HttpStatus.OK);
    }

}
