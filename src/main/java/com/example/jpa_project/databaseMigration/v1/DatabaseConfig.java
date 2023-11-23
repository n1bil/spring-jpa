package com.example.jpa_project.databaseMigration.v1;

import com.example.jpa_project.entity.Role;
import com.example.jpa_project.entity.User;
import com.example.jpa_project.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DatabaseConfig {

    private RoleRepository repository;

    public void fillRoleTable() {
        Role admin = new Role();
        admin.setName("Admin");
        repository.save(admin);

        Role user = new Role();
        user.setName("User");
        repository.save(user);
    }
}
