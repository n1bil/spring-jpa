package com.example.jpa_project.validation;

import com.example.jpa_project.payload.task.TaskCreateRequestDTO;

public class ToDoDescriptionMaxLength {

    public void validation(TaskCreateRequestDTO request){
        if (request.getDescription().length() > 30) {
            throw new IllegalStateException("ToDo description length more than 30 ...");
        }
    }
}
