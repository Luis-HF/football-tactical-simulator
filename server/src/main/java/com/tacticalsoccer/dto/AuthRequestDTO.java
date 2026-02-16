package com.tacticalsoccer.dto;

import jakarta.validation.constraints.*;

public record AuthRequestDTO(
        @NotBlank
        @Size(max = 20)
        String username,

        @NotBlank
        @Size(min = 10, max = 20)
        String password
)
{}
