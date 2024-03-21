package com.example.basiccrud.services;

import com.example.basiccrud.domain.user.User;
import com.example.basiccrud.domain.user.dtos.CreateUserDTO;
import com.example.basiccrud.domain.user.dtos.ReturnUserDTO;
import com.example.basiccrud.domain.user.dtos.UpdateUserDTO;
import com.example.basiccrud.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User(createUserDTO.name(), createUserDTO.email(), createUserDTO.password(), createUserDTO.role());
        return userRepository.save(user);
    }


    public List<ReturnUserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new ReturnUserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()))
                .collect(Collectors.toList());
    }

    public ReturnUserDTO getUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        return new ReturnUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }


    public void updateUser(String id, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return;
        }
        User user = optionalUser.get();

        user.setName(updateUserDTO.name() != null ? updateUserDTO.name() : user.getName());
        user.setEmail(updateUserDTO.email() != null ? updateUserDTO.email() : user.getEmail());
        user.setPassword(updateUserDTO.password() != null ? updateUserDTO.password() : user.getPassword());
        user.setRole(updateUserDTO.role() != null ? updateUserDTO.role() : user.getRole());
        user.setUpdatedAt(new java.util.Date());

        userRepository.save(user);
    }

    public void deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return;
        }
        userRepository.delete(optionalUser.get());
    }

}
