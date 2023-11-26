package com.example.jpa_project.payload.user;

import com.example.jpa_project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private Role roleName;

}
