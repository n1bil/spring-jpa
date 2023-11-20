package com.example.jpa_project.repository;

import com.example.jpa_project.entity.Task;
import com.example.jpa_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUser(User user);
}
