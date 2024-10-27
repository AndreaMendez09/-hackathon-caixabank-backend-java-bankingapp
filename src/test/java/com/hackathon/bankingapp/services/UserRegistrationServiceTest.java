package com.hackathon.bankingapp.services;

import com.hackathon.bankingapp.DTO.UserDTO;
import com.hackathon.bankingapp.Entities.api.req.UserRegistrationReq;
import com.hackathon.bankingapp.Entities.api.res.UserRegistrationRes;
import com.hackathon.bankingapp.exceptions.UserAlreadyExistsException;
import com.hackathon.bankingapp.mappers.UserMapper;
import com.hackathon.bankingapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRegistrationService userRegistrationService;

    private UserRegistrationReq userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRegistrationReq();
        userRequest.setName("Nuwe Test");
        userRequest.setPassword("NuweTest1$");
        userRequest.setEmail("nuwe@nuwe.com");
        userRequest.setAddress("Main St");
        userRequest.setPhoneNumber("666888116");
    }

    @Test
    void should_beOk_when_validDataProvided() {
        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())).thenReturn(false);

        UserRegistrationRes userRegistrationRes = new UserRegistrationRes();
        userRegistrationRes.setAccountNumber("19b332");
        when(userMapper.toEntity(userRequest)).thenReturn(new UserDTO());
        when(userMapper.toResponse(any())).thenReturn(userRegistrationRes);

        when(passwordEncoder.encode(userRequest.getPassword())).thenReturn("hashedPassword");

        UserRegistrationRes result = userRegistrationService.registerUser(userRequest);

        assertNotNull(result);
        assertEquals("19b332", result.getAccountNumber());
        verify(userRepository).save(any());
    }

    @Test
    void should_throwException_when_requiredFieldAreNull() {
        userRequest.setName(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userRegistrationService.registerUser(userRequest);
        });
        assertEquals("No empty fields allowed", exception.getMessage());
    }

    @Test
    void should_throwException_when_invalidEmail() {
        userRequest.setEmail("invalidEmail");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userRegistrationService.registerUser(userRequest);
        });
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void should_throwException_when_EmailAlreadyExists() {
        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userRegistrationService.registerUser(userRequest);
        });
        assertEquals("Email already exists", exception.getMessage());
    }

    @Test
    void should_throwException_when_PhoneNumberAlreadyExists() {
        when(userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())).thenReturn(true);

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            userRegistrationService.registerUser(userRequest);
        });
        assertEquals("Phone number already exists", exception.getMessage());
    }
}

