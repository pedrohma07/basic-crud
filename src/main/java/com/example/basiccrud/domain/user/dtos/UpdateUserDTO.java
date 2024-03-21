package com.example.basiccrud.domain.user.dtos;

import java.util.Date;

public record UpdateUserDTO(String name, String email, String password, String role, Date updatedAt) {
}
