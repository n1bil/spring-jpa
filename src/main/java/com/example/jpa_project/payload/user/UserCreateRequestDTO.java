package com.example.jpa_project.payload.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateRequestDTO {

    private String username;
    private String password;
    private String email;
}
