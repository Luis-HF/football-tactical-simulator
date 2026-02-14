package com.tacticalsoccer.services;

import com.tacticalsoccer.dto.AuthResponseDTO;
import com.tacticalsoccer.exceptions.InvalidCredentialsException;
import com.tacticalsoccer.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import com.tacticalsoccer.dto.AuthRequestDTO;

@Service
public class AuthService {
    private final AccountRepository repository;
    public AuthService (AccountRepository repository){this.repository=repository;}

    public AuthResponseDTO authLogin(AuthRequestDTO data) {
        return repository.findByUsername(data.username())
                .filter(user -> user.getPassword().equals(data.password()))
                .map(user -> new AuthResponseDTO(user.getId(), user.getUsername()))
                .orElseThrow(() -> new InvalidCredentialsException("User or password invalid."));
    }
}
