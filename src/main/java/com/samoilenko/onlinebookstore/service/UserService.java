package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.UserRegistrationRequestDto;
import com.samoilenko.onlinebookstore.dto.UserResponseDto;
import com.samoilenko.onlinebookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
