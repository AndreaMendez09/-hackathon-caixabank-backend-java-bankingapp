package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.DTO.UserDTO;
import com.hackathon.bankingapp.repositories.UserRepository;
import com.hackathon.bankingapp.utils.JWTTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTTokenProvider jwtTokenProvider;

    public String authenticate(String identifier, String password) {
        UserDTO user;
        if (identifier.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            user = userRepository.findByEmail(identifier)
                    .orElseThrow(() -> new IllegalArgumentException("User not found for the given identifier: " + identifier));
        }else {
            user = userRepository.findByAccountNumber(UUID.fromString(identifier))
                    .orElseThrow(() -> new IllegalArgumentException("User not found for the given identifier: " + identifier));
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getAccountNumber(), password);

        try {
            authentication = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtTokenProvider.generateToken(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad credentials");
        }
    }
}
