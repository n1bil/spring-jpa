package com.example.jpa_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title must not be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Title must contain latin letters and digit only")
    @Size(min = 3, max = 15, message = "Task name length must be between 3 and 15 characters")
    private String title;

    @NotBlank(message = "Title must not be empty")
    @Size(min = 1, max = 25, message = "Task name length must be between 3 and 15 characters")
    private String description;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Deadline name must be not null")
    private LocalDateTime deadline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private TaskStatus status;

}
