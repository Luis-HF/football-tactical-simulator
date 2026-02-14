package com.tacticalsoccer.services;

import com.tacticalsoccer.dto.AccountRequestDTO;
import com.tacticalsoccer.models.Account;
import com.tacticalsoccer.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount(AccountRequestDTO data) {

        Account account = new Account();
        account.setUsername(data.username());
        account.setEmail(data.email());
        account.setPassword(data.password());

        repository.save(account);
    }
}