package com.tacticalsoccer.dto;

import jakarta.validation.constraints.NotBlank;


public record LobbyCreateRequestDTO(

        @NotBlank
        Long id

){}
