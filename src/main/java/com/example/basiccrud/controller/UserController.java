package com.example.basiccrud.controller;

import com.example.basiccrud.domain.user.User;
import com.example.basiccrud.domain.user.dtos.CreateUserDTO;
import com.example.basiccrud.domain.user.dtos.ReturnUserDTO;
import com.example.basiccrud.domain.user.dtos.UpdateUserDTO;
import com.example.basiccrud.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserDTO> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        System.out.println("createUserDTO = " + createUserDTO);
        return ResponseEntity.ok(this.userService.createUser(createUserDTO));
    }

    @GetMapping
    public List<ReturnUserDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnUserDTO> getUser(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UpdateUserDTO updateUserDTO) {
        this.userService.updateUser(id, updateUserDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
