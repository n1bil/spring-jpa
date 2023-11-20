package com.example.jpa_project.utils;

import com.example.jpa_project.entity.User;
import com.example.jpa_project.payload.user.UserCreateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import com.example.jpa_project.payload.user.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserConverters {

    public UserCreateResponseDTO toCreateDto(User user) {
        return new UserCreateResponseDTO(user.getId(), user.getUsername(), LocalDateTime.now(),LocalDateTime.now());
    }

    public UserResponseDTO toDto(User user) {
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(),LocalDateTime.now(),LocalDateTime.now());
    }

    public User fromDto(UserCreateRequestDTO dto) {
        User newUser = new User();
        if (dto.getUsername() != null) {
            newUser.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            newUser.setPassword(dto.getPassword());
        }
        if (dto.getEmail() != null) {
            newUser.setEmail(dto.getEmail());
        }

        return newUser;
    }
}
