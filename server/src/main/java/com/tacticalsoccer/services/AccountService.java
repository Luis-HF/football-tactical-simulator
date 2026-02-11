package com.tacticalsoccer.services;

import com.tacticalsoccer.dto.AccountRequestDTO;
import com.tacticalsoccer.dto.AccountResponseDTO;
import com.tacticalsoccer.models.Account;
import com.tacticalsoccer.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import com.tacticalsoccer.exceptions.ResourceConflictException;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountResponseDTO createAccount(AccountRequestDTO data) {

        if (repository.existsByEmail(data.email())) {
            throw new ResourceConflictException("O e-mail informado já está em uso.");
        } else if (repository.existsByUsername(data.username())){
            throw new ResourceConflictException("O nome de usuário informado já está em uso.");
        }

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