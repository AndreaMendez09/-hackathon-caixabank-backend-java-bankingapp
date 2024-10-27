package com.hackathon.bankingapp.repositories;

import com.hackathon.bankingapp.DTO.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDTO, Integer> {
    Optional<UserDTO> findByEmail(String email);
    Optional<UserDTO> findByPhoneNumber(String phoneNumber);
    Optional<UserDTO> findByAccountNumber(UUID accountNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
