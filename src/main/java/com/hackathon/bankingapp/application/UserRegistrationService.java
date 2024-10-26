package com.hackathon.bankingapp.application;

import com.hackathon.bankingapp.domain.exceptions.UserAlreadyExistsException;
import com.hackathon.bankingapp.domain.mappers.UserMapper;
import com.hackathon.bankingapp.infraestructure.api.req.UserRegistrationReq;
import com.hackathon.bankingapp.infraestructure.api.res.UserRegistrationRes;
import com.hackathon.bankingapp.infraestructure.data.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRegistrationRes registerUser(UserRegistrationReq request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null
                || request.getPhoneNumber() == null) {
            throw new IllegalArgumentException("No empty fields allowed");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new UserAlreadyExistsException("Phone number already exists");
        }

        var userDto = userMapper.toEntity(request);

        userRepository.save(userDto);

        return userMapper.toResponse(userDto);
    }

}
