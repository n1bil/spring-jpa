package com.example.jpa_project.service.serviceImpl;

import com.example.jpa_project.entity.Role;
import com.example.jpa_project.entity.User;
import com.example.jpa_project.exception.IsAlreadyExistException;
import com.example.jpa_project.exception.NotFoundException;
import com.example.jpa_project.payload.user.UserCreateRequestDTO;
import com.example.jpa_project.payload.user.UserCreateResponseDTO;
import com.example.jpa_project.payload.user.UserResponseDTO;
import com.example.jpa_project.repository.RoleRepository;
import com.example.jpa_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .toList();
    }

    public UserResponseDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found with username: " + username));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO findByUserEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with email: " + email));

        return modelMapper.map(user, UserResponseDTO.class);
    }

    public User findUserByUsernameForCreateTask(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found with username: " + username));
    }

    public UserCreateResponseDTO createUser(UserCreateRequestDTO requestDTO) {
        if (userRepository.findByUsername(requestDTO.getUsername()).isEmpty()) {
            User newUser = modelMapper.map(requestDTO, User.class);

            Optional<Role> defaultRole = roleRepository.findByName("USER");

            if (defaultRole.isPresent()) {
                newUser.setRole(defaultRole.get());
            } else {
                throw new NotFoundException("Role 'User' not found.");
            }

            User savedUser = userRepository.save(newUser);
            return modelMapper.map(savedUser, UserCreateResponseDTO.class);
        } else {
            throw new IsAlreadyExistException("User with email " + requestDTO.getEmail() + " is already exist!");
        }
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID: " + id + " is not found!"));

        userRepository.delete(user);
    }

}
