package com.example.jpa_project.service.serviceImpl;

import com.example.jpa_project.entity.User;
import com.example.jpa_project.exception.IsAlreadyExistException;
import com.example.jpa_project.exception.NotFoundException;
import com.example.jpa_project.payload.user.UserCreateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import com.example.jpa_project.payload.user.UserResponseDTO;
import com.example.jpa_project.repository.UserRepository;
import com.example.jpa_project.utils.UserConverters;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private final UserRepository repository;
    private final UserConverters converter;

    // получить список всех пользователей

    public List<UserResponseDTO> findAllUsers() {
        return repository.findAll().stream()
                .map(converter::toDto)
                .toList();
    }

    //найти пользователя по email
    public UserResponseDTO findByUserEmail(String email){
        User manager = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with email: " + email));
        return converter.toDto(manager);
    }

    // Создать нового пользователя

    public UserCreateResponseDTO createUser(UserCreateRequestDTO requestDTO) {
        if (repository.findByEmail(requestDTO.getEmail()).isEmpty()) {
            User newUser = converter.fromDto(requestDTO);
            newUser = repository.save(newUser);
            return converter.toCreateDto(newUser);
        } else {
            throw new IsAlreadyExistException("User with email " + requestDTO.getEmail() + " is already exist!");
        }
    }

    public void deleteUser(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID: " + id + " is not found!"));

        repository.delete(user);
    }

}
