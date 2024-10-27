package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.DTO.AccountDTO;
import com.hackathon.bankingapp.exceptions.UserAlreadyExistsException;
import com.hackathon.bankingapp.mappers.UserMapper;
import com.hackathon.bankingapp.Entities.api.req.UserRegistrationReq;
import com.hackathon.bankingapp.Entities.api.res.UserInfoRes;
import com.hackathon.bankingapp.repositories.AccountRepository;
import com.hackathon.bankingapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserInfoRes registerUser(UserRegistrationReq request) {
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

        var newAccount = new AccountDTO();
        newAccount.setAccountNumber(userDto.getAccountNumber());
        newAccount.setBalance(BigDecimal.valueOf(0));
        accountRepository.save(newAccount);

        return userMapper.toResponse(userDto);
    }

}
