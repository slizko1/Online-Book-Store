package com.samoilenko.onlinebookstore.controller;

import com.samoilenko.onlinebookstore.dto.UserRegistrationRequestDto;
import com.samoilenko.onlinebookstore.dto.UserResponseDto;
import com.samoilenko.onlinebookstore.exception.RegistrationException;
import com.samoilenko.onlinebookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        UserResponseDto register = userService.register(request);
        return register;
    }
}
