package com.hackathon.bankingapp.infraestructure.data.repo;

import com.hackathon.bankingapp.domain.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO, Integer> {
    Optional<UserDTO> findByEmail(String email);
    Optional<UserDTO> findByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
