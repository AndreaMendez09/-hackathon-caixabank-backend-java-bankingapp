package com.hackathon.bankingapp.repositories;

import com.hackathon.bankingapp.DTO.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountDTO, UUID> {

    Optional<AccountDTO> findByAccountNumber(UUID accountNumber);
}
