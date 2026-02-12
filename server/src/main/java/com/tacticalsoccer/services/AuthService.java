package com.tacticalsoccer.services;

import com.tacticalsoccer.dto.AuthResponseDTO;
import com.tacticalsoccer.models.Account;
import com.tacticalsoccer.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import com.tacticalsoccer.dto.AuthRequestDTO;
import com.tacticalsoccer.exceptions.ResourceConflictException;

@Service
public class AuthService {
    private final AccountRepository repository;
    public AuthService (AccountRepository repository){this.repository=repository;}

    public AuthResponseDTO authLogin(AuthRequestDTO data) {
        return repository.findByUsername(data.username())
                .filter(user -> user.getPassword().equals(data.password()))
                .map(user -> new AuthResponseDTO(user.getId(), user.getUsername()))
                .orElseThrow(() -> new ResourceConflictException("Credenciais incorretas."));
    }
}
