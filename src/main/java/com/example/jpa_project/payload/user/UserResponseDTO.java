package com.example.jpa_project.payload.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private LocalDateTime createManagerDate;
    private LocalDateTime lastUpdate;

}
