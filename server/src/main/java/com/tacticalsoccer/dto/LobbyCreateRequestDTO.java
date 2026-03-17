package com.tacticalsoccer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record LobbyCreateRequestDTO(

        @NotNull
        Long id

){}
