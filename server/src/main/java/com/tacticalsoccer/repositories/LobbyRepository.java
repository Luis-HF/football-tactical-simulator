package com.tacticalsoccer.repositories;

import com.tacticalsoccer.models.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LobbyRepository extends JpaRepository {
    Optional<Lobby> findByCode(String code);
}
