package com.example.jpa_project.payload.task;

import com.example.jpa_project.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequestDTO {

    private Integer id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private TaskStatus status;
    private String managerName;
}
