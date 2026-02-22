package com.example.back21.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


public record AccountRequestDTO(
        @NotBlank
        @Size(max=20)
        String username,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min=10, max=20)
        String password
){}
