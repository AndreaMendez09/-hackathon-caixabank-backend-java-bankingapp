package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.exceptions.UserAlreadyExistsException;
import com.hackathon.bankingapp.mappers.UserMapper;
import com.hackathon.bankingapp.Entities.api.req.UserRegistrationReq;
import com.hackathon.bankingapp.Entities.api.res.UserRegistrationRes;
import com.hackathon.bankingapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationRes registerUser(UserRegistrationReq request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null
                || request.getPhoneNumber() == null) {
            throw new IllegalArgumentException("No empty fields allowed");
        }

        if (!request.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new UserAlreadyExistsException("Phone number already exists");
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        var userDto = userMapper.toEntity(request);

        userRepository.save(userDto);

        return userMapper.toResponse(userDto);
    }

}
