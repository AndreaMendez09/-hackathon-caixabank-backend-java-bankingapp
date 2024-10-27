package com.hackathon.bankingapp.Entities.api.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@Component
@NoArgsConstructor
public class AccountInfoRes {
    private UUID accountNumber;
    private BigDecimal balance;
}
