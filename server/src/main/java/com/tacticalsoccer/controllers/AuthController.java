package com.tacticalsoccer.controllers;

import com.tacticalsoccer.services.AuthService;
import com.tacticalsoccer.dto.AuthRequestDTO;
import com.tacticalsoccer.dto.AuthResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
;

@RestController
@RequestMapping("/api/v1/auth/login")
public class AuthController {

    private final AuthService authService;

    public AuthController (AuthService authService){this.authService=authService;}

    @PostMapping
    public ResponseEntity<AuthResponseDTO> login (@Valid @RequestBody AuthRequestDTO data){
        AuthResponseDTO response = authService.authLogin(data);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
