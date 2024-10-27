package com.hackathon.bankingapp.controllers;

import com.hackathon.bankingapp.services.UserRegistrationService;
import com.hackathon.bankingapp.Entities.api.req.UserRegistrationReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationReq user) {
        try {
            var registeredUser = userRegistrationService.registerUser(user);
            return ResponseEntity.ok().body(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
