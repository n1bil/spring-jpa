package com.example.jpa_project.payload.user;

import lombok.Data;

@Data
public class UserCreateRequestDTO {

    private String username;
    private String password;
    private String email;
}
