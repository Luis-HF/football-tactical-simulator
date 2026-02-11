package com.tacticalsoccer.controllers;

import com.tacticalsoccer.services.AccountService;
import com.tacticalsoccer.dto.AccountRequestDTO;

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
    public ResponseEntity<Void> register(@Valid @RequestBody AccountRequestDTO data) {
        accountService.createAccount(data);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}