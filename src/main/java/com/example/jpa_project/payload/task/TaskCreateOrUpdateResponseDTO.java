package com.example.jpa_project.payload.task;

import com.example.jpa_project.entity.TaskStatus;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
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
    private UserCreateResponseDTO userCreateResponseDTO;

}
