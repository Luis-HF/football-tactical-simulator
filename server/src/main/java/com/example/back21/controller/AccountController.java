package com.example.back21.controller;

import com.example.back21.service.AccountService;
import com.example.back21.dto.AccountRequestDTO;
import com.example.back21.dto.AccountResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> register(@Valid @RequestBody AccountRequestDTO data) {
        AccountResponseDTO response = accountService.createAccount(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}