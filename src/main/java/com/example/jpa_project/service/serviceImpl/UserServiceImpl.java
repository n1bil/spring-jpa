package com.example.jpa_project.service.serviceImpl;

import com.example.jpa_project.entity.User;
import com.example.jpa_project.exception.IsAlreadyExistException;
import com.example.jpa_project.exception.NotFoundException;
import com.example.jpa_project.payload.user.UserCreateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import com.example.jpa_project.payload.user.UserResponseDTO;
import com.example.jpa_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private final UserRepository repository;
    private final ModelMapper modelMapper;


    public List<UserResponseDTO> findAllUsers() {
        return repository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList();
    }

    public UserResponseDTO findByUserEmail(String email){
        User user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with email: " + email));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserCreateResponseDTO createUser(UserCreateRequestDTO requestDTO) {
        if (repository.findByEmail(requestDTO.getEmail()).isEmpty()) {
            User newUser = modelMapper.map(requestDTO, User.class);
            newUser = repository.save(newUser);
            return modelMapper.map(newUser, UserCreateResponseDTO.class);
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
