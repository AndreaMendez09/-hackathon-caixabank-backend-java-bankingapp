package com.hackathon.bankingapp.mappers;

import com.hackathon.bankingapp.DTO.AccountDTO;
import com.hackathon.bankingapp.Entities.api.res.AccountInfoRes;
import com.hackathon.bankingapp.Entities.api.res.UserInfoRes;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public AccountInfoRes toResponse(AccountDTO account) {
        AccountInfoRes response = new AccountInfoRes();
        response.setAccountNumber(account.getAccountNumber());
        response.setBalance(account.getBalance());
        return response;
    }
}
