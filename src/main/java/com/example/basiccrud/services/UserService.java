package com.example.basiccrud.services;

import com.example.basiccrud.domain.user.User;
import com.example.basiccrud.domain.user.dtos.CreateUserDTO;
import com.example.basiccrud.domain.user.dtos.ReturnUserDTO;
import com.example.basiccrud.domain.user.dtos.UpdateUserDTO;
import com.example.basiccrud.exceptions.NotFoundException;
import com.example.basiccrud.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public CreateUserDTO createUser(CreateUserDTO createUserDTO) {
        User user = new User(createUserDTO.name(), createUserDTO.email(), createUserDTO.password(), createUserDTO.role());
        userRepository.save(user);
        return createUserDTO;
    }


    public List<ReturnUserDTO> getAllUsers(Integer numPage, Integer numRegisters){
        // PageRequest initial page is 0
        Pageable pageRequest = PageRequest.of( numPage, numRegisters);
        Page<User> users = userRepository.findAll(pageRequest);
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
            throw new NotFoundException("User not found");
        }
        User user = optionalUser.get();
        return new ReturnUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public ReturnUserDTO getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return new ReturnUserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            );
        }
        return null;
    }

    public void updateUser(String id, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = optionalUser.get();

        user.setName(updateUserDTO.name() != null ? updateUserDTO.name() : user.getName());
        user.setPassword(updateUserDTO.password() != null ? updateUserDTO.password() : user.getPassword());
        user.setRole(updateUserDTO.role() != null ? updateUserDTO.role() : user.getRole());
        user.setUpdatedAt(new java.util.Date());

        userRepository.save(user);
    }

    public void updateUserByEmail(String email, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = optionalUser.get();

        user.setName(updateUserDTO.name() != null ? updateUserDTO.name() : user.getName());
        user.setPassword(updateUserDTO.password() != null ? updateUserDTO.password() : user.getPassword());
        user.setRole(updateUserDTO.role() != null ? updateUserDTO.role() : user.getRole());
        user.setUpdatedAt(new java.util.Date());

        userRepository.save(user);
    }

    public void deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        userRepository.delete(optionalUser.get());
    }

}
