package com.example.jpa_project.payload.task;

import com.example.jpa_project.entity.TaskStatus;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateOrUpdateResponseDTO {

    private Integer id;
    private String title;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private LocalDateTime deadline;
    private TaskStatus status;
    private UserCreateResponseDTO managerCreateResponseDTO;

}
