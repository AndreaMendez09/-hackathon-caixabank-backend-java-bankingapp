package com.hackathon.bankingapp.controllers;

import com.hackathon.bankingapp.DTO.UserDTO;
import com.hackathon.bankingapp.Entities.api.res.UserInfoRes;
import com.hackathon.bankingapp.services.UserRetrieveInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final UserRetrieveInfoService userRetrieveInfoService;

    @GetMapping("/user")
    public UserInfoRes getUserInfo(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");

        return userRetrieveInfoService.getUserInfo(token);
    }


}
