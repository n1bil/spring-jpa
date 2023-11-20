package com.example.jpa_project.payload.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserCreateResponseDTO {

    private Integer id;
    private String username;
    private LocalDateTime createManagerDate;
    private LocalDateTime lastUpdate;
}
