package com.example.jpa_project.payload.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequestDTO {

    private String title;
    private String description;
    private LocalDateTime deadline;
    private String username;
}
