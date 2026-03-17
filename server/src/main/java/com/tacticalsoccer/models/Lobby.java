package com.tacticalsoccer.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Lobbies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "host_id", nullable = false)
    private Account host;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Account guest;

    @Column(nullable = false)
    private Integer p1 = 0;

    @Column(nullable = false)
    private Integer p2 = 0;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime endedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LobbyStatus status;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Account winner;

    @Column(nullable = false)
    private LocalDateTime lastActivity;
}