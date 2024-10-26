package com.hackathon.bankingapp.domain.mappers;

import com.hackathon.bankingapp.domain.dto.UserDTO;
import com.hackathon.bankingapp.infraestructure.api.req.UserRegistrationReq;
import com.hackathon.bankingapp.infraestructure.api.res.UserRegistrationRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toEntity(UserRegistrationReq request);

    UserRegistrationRes toResponse(UserDTO user);
}