package com.example.basiccrud.domain.user.dtos;
import com.example.basiccrud.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(

        @NotEmpty(message = "Name cannot be empty")
        String name,
        @Email(message = "Email should be valid")
        String email,
        @NotEmpty
        String password,

        @NotNull
        UserRole role
) {
}
