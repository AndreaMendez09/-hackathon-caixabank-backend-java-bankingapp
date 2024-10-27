package com.hackathon.bankingapp.Entities.api.req;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
public class UserRegistrationReq {
    private String name;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
}
