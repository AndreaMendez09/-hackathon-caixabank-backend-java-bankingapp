package com.hackathon.bankingapp.mappers;

import com.hackathon.bankingapp.DTO.UserDTO;
import com.hackathon.bankingapp.Entities.api.req.UserRegistrationReq;
import com.hackathon.bankingapp.Entities.api.res.UserRegistrationRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //@Mapping(target = "passwordHash", source = "password")
    UserDTO toEntity(UserRegistrationReq request);
    //@Mapping(target = "hashedPassword", source = "passwordHash")
    UserRegistrationRes toResponse(UserDTO user);
}