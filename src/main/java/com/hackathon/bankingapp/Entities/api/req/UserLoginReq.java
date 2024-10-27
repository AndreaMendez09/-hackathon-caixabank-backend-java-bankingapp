package com.hackathon.bankingapp.Entities.api.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Component
@NoArgsConstructor
public class UserLoginReq {
    private String identifier;
    private String password;
}
