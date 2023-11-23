package com.example.jpa_project.payload.task;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequestDTO {

    private String title;
    private String description;
    private LocalDateTime deadline;
    private String username;
}
