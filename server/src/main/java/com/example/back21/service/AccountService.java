package com.example.back21.service;

import com.example.back21.dto.AccountRequestDTO;
import com.example.back21.dto.AccountResponseDTO;
import com.example.back21.entities.Account;
import com.example.back21.repositories.AccountRepository;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountResponseDTO createAccount(AccountRequestDTO data) {

        Account account = new Account();
        account.setUsername(data.username());
        account.setEmail(data.email());
        account.setPassword(data.password());

        Account accountSaved = repository.save(account);

        return new AccountResponseDTO(
                accountSaved.getId(),
                accountSaved.getUsername(),
                accountSaved.getEmail()
        );
    }
}