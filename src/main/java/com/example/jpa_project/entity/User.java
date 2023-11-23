package com.example.jpa_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "User name must not be empty")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "User name must contain latin letters and digit only")
    private String username;

    @NotBlank(message = "Password must not be empty")
    @Pattern(regexp = "^[A-Za-z0-9!@#%$^&*()]+$", message = "Password must contain latin letters and digit some special characters")
    @Size(min = 5, max = 15, message = "Password length must be between 5 and 15 symbols")
    private String password;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Must not be empty")
    private String email;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime lastVisitDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
}
