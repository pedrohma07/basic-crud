package com.example.basiccrud.controller;

import com.example.basiccrud.domain.user.User;
import com.example.basiccrud.domain.user.dtos.CreateUserDTO;
import com.example.basiccrud.domain.user.dtos.ReturnUserDTO;
import com.example.basiccrud.domain.user.dtos.UpdateUserDTO;
import com.example.basiccrud.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    // Post method to create a new user
    @PostMapping
    public String createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return "User created";
    }

    @GetMapping
    public List<ReturnUserDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnUserDTO> getUser(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }


    // Add a new method to update a user
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UpdateUserDTO updateUserDTO) {
        this.userService.updateUser(id, updateUserDTO);
        return ResponseEntity.ok("User updated");
    }
}
