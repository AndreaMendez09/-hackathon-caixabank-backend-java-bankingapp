package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.DTO.UserDTO;
import com.hackathon.bankingapp.Entities.api.res.UserInfoRes;
import com.hackathon.bankingapp.mappers.UserMapper;
import com.hackathon.bankingapp.repositories.UserRepository;
import com.hackathon.bankingapp.utils.JWTTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserRetrieveInfoService {


    private final UserRepository userRepository;
    private final JWTTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    public UserInfoRes getUserInfo(String token) {
        String accountNumber = jwtTokenProvider.getAccountNumberFromToken(token);

        var userInfo = userRepository.findByAccountNumber(UUID.fromString(accountNumber))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return userMapper.toResponse(userInfo);
    }
}
