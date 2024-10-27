package com.hackathon.bankingapp.controllers;

import com.hackathon.bankingapp.Entities.api.req.UserLoginReq;
import com.hackathon.bankingapp.Entities.api.res.UserLoginRes;
import com.hackathon.bankingapp.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginReq request) {
        try {
            String token = authenticationService.authenticate(request.getIdentifier(), request.getPassword());
            return ResponseEntity.ok(new UserLoginRes(token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Bad credentials");
        }
    }
}