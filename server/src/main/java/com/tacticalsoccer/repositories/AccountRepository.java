package com.tacticalsoccer.repositories; //package padrao

import com.tacticalsoccer.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}