package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.Entities.api.res.AccountInfoRes;
import com.hackathon.bankingapp.Entities.api.res.UserInfoRes;
import com.hackathon.bankingapp.mappers.AccountMapper;
import com.hackathon.bankingapp.mappers.UserMapper;
import com.hackathon.bankingapp.repositories.AccountRepository;
import com.hackathon.bankingapp.repositories.UserRepository;
import com.hackathon.bankingapp.utils.JWTTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class AccountRetrieveInfoService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final JWTTokenProvider jwtTokenProvider;

    public AccountInfoRes getAccountInfo(String token) {
        String accountNumber = jwtTokenProvider.getAccountNumberFromToken(token);

        var accountInfo = accountRepository.findByAccountNumber(UUID.fromString(accountNumber))
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return accountMapper.toResponse(accountInfo);
    }
}


