package com.example.basiccrud.domain.user.dtos;
import com.example.basiccrud.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(

        @NotBlank(message = "Name cannot be empty")
        String name,
        @Email(message = "Email should be valid")
        String email,
        @NotEmpty(message = "Password cannot be empty")
        String password,

        @NotNull
        UserRole role
) {
}
