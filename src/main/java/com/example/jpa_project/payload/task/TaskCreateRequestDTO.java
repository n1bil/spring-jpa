package com.example.jpa_project.payload.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateRequestDTO {

    @NotBlank(message = "Title must not be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Title must contain latin letters and digit only")
    @Size(min = 3, max = 15, message = "Task name length must be between 3 and 15 characters")
    private String title;

    @NotBlank(message = "Title must not be empty")
    @Size(min = 1, max = 25, message = "Description length must be between 3 and 15 characters")
    private String description;


    @NotNull(message = "Deadline name must be not null")
    private LocalDateTime deadline;

    private String username;
}
