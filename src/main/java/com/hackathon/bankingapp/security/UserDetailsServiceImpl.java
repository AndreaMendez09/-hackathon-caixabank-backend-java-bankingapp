package com.hackathon.bankingapp.security;

import com.hackathon.bankingapp.DTO.UserDTO;
import com.hackathon.bankingapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountNumber) throws UsernameNotFoundException {
        UUID accountUUID = UUID.fromString(accountNumber);
        UserDTO user = userRepository.findByAccountNumber(accountUUID)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with account number: " + accountNumber));
        return new org.springframework.security.core.userdetails.User(
                "Prueba",
                user.getPasswordHash(),
                new ArrayList<>());
    }
}
