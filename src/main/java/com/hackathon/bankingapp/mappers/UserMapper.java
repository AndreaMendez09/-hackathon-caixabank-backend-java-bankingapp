package com.hackathon.bankingapp.mappers;

import com.hackathon.bankingapp.DTO.UserDTO;
import com.hackathon.bankingapp.Entities.api.req.UserRegistrationReq;
import com.hackathon.bankingapp.Entities.api.res.UserInfoRes;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDTO toEntity(UserRegistrationReq request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(request.getName());
        userDTO.setEmail(request.getEmail());
        userDTO.setPhoneNumber(request.getPhoneNumber());
        userDTO.setAddress(request.getAddress());
        userDTO.setPasswordHash(request.getPassword());
        return userDTO;
    }

    public UserInfoRes toResponse(UserDTO user) {
        UserInfoRes response = new UserInfoRes();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAddress(user.getAddress());
        response.setAccountNumber(user.getAccountNumber().toString());
        response.setHashedPassword(user.getPasswordHash());
        return response;
    }
}