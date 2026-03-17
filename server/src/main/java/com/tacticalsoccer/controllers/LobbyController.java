package com.tacticalsoccer.controllers;

import com.tacticalsoccer.dto.LobbyCreateResponseDTO;
import com.tacticalsoccer.models.Lobby;
import com.tacticalsoccer.services.LobbyService;
import com.tacticalsoccer.dto.LobbyCreateRequestDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/lobbies")
public class LobbyController {

    private final LobbyService lobbyService;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping
    public ResponseEntity<LobbyCreateResponseDTO> createLobby(@Valid @RequestBody LobbyCreateRequestDTO data) {
        Lobby lobby = lobbyService.createLobby(data);
        LobbyCreateResponseDTO response = new LobbyCreateResponseDTO(lobby.getCode());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}