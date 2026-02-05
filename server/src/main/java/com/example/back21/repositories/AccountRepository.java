package com.example.back21.repositories; //package padrao

import com.example.back21.entities.Account; /*import de classe que sera usada
como entidade no JPA*/

import org.springframework.data.jpa.repository.JpaRepository; //o jpa em si
import org.springframework.stereotype.Repository;//não sei

@Repository//não sei também
public interface AccountRepository extends JpaRepository<Account, Long> {
}/*aqui é uma interface AccountRepository que está herdando de JpaRepository, eu
ainda não entendo a sintaxe, apenas o conceito*/