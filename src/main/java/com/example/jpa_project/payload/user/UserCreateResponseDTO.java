package com.example.jpa_project.payload.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponseDTO {

    private Integer id;
    private String username;
    private String email;

}
