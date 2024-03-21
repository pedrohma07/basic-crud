package com.example.basiccrud.domain.user.dtos;
import com.example.basiccrud.domain.enums.UserRole;
import java.util.Date;

public record UpdateUserDTO(String name, String email, String password, UserRole role, Date updatedAt) {
}
