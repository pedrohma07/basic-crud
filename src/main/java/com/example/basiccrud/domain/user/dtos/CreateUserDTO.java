package com.example.basiccrud.domain.user.dtos;

public record CreateUserDTO(String name, String email, String password, String role) {
}
