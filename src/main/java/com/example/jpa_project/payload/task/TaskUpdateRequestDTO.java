package com.example.jpa_project.payload.task;

import com.example.jpa_project.entity.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequestDTO {

    private Integer id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private TaskStatus status;
    private String username;
}
