package com.example.jpa_project.utils;

import com.example.jpa_project.entity.Task;
import com.example.jpa_project.payload.task.TaskCreateOrUpdateResponseDTO;
import com.example.jpa_project.payload.task.TaskCreateRequestDTO;
import com.example.jpa_project.payload.task.TaskResponseDTO;
import com.example.jpa_project.payload.task.TaskUpdateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskConverters {

    public TaskCreateOrUpdateResponseDTO toCreateDto(Task task) {
        TaskCreateOrUpdateResponseDTO dto = new TaskCreateOrUpdateResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCreateDate(task.getCreateDate());
        dto.setLastUpdate(task.getLastUpdate());
        dto.setDeadline(task.getDeadline());
        dto.setStatus(task.getStatus());
        dto.setManagerCreateResponseDTO(new UserCreateResponseDTO(
                task.getUser().getId(),
                task.getUser().getUsername(),
                task.getUser().getCreationDate(),
                task.getUser().getLastVisitDate()));


        return dto;
    }

    public TaskResponseDTO toDto(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCreateDate(task.getCreateDate());
        dto.setLastUpdate(task.getLastUpdate());
        dto.setDeadline(task.getDeadline());
        dto.setStatus(task.getStatus());

        return dto;
    }

    public Task fromCreateRequest(TaskCreateRequestDTO dto) {
        Task task = new Task();
        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }
        if (dto.getDeadline() != null) {
            task.setDeadline(dto.getDeadline());
        }

        return task;
    }

    public Task fromUpdateRequest(TaskUpdateRequestDTO dto) {
        Task task = new Task();
        if (dto.getId() != null) {
            task.setId(dto.getId());
        }

        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }
        if (dto.getDeadline() != null) {
            task.setDeadline(dto.getDeadline());
        }
        if (dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        }
        return task;
    }
}
