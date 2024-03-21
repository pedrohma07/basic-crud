package com.example.basiccrud.domain.user.dtos;

import com.example.basiccrud.domain.enums.UserRole;


public record ReturnUserDTO(String id, String name, String email, UserRole role) {
}
