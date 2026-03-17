package com.tacticalsoccer.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import com.tacticalsoccer.models.LobbyStatus;
import com.tacticalsoccer.dto.LobbyCreateRequestDTO;
import com.tacticalsoccer.models.Lobby;
import com.tacticalsoccer.repositories.LobbyRepository;
import org.springframework.stereotype.Service;
import com.tacticalsoccer.models.Account;
import com.tacticalsoccer.repositories.AccountRepository;

@Service
public class LobbyService {

    private final LobbyRepository lo_repository;
    private final AccountRepository ac_repository;

    public LobbyService(LobbyRepository lo_repository, AccountRepository ac_repository) {
        this.lo_repository = lo_repository;
        this.ac_repository = ac_repository;
    }

    public Lobby createLobby(LobbyCreateRequestDTO data) {
        Account host = findAccount(data);
        Lobby lobby = new Lobby();
        lobby.setHost(host);
        lobby.setCreatedAt(LocalDateTime.now());
        lobby.setStatus(LobbyStatus.WAITING);
        String code = genCode();
        lobby.setCode(code);
        updateLastActivity(lobby);

        lo_repository.save(lobby);
        return lobby;
    }

    private Account findAccount(LobbyCreateRequestDTO data){
        return ac_repository.findById(data.id())
                .orElseThrow(() -> new RuntimeException("User does not exists"));
    }

    //metodo para gerar um codigo unico da partida, feito por llm.
    private String genCode(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        String finalCode;
        boolean exists;

        do {
            codeBuilder.setLength(0);
            for (int i = 0; i < 10; i++) {
                int index = random.nextInt(alphabet.length());
                codeBuilder.append(alphabet.charAt(index));
            }
            finalCode = codeBuilder.toString();

            exists = lo_repository.findByCode(finalCode).isPresent();

        } while (exists); // Se existir, ele volta para o 'do' e gera outro

        return finalCode;
    }

    private void updateLastActivity(Lobby lobby) {
        lobby.setLastActivity(LocalDateTime.now());
    }
}