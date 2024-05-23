package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.userdtos.UserRegistrationRequestDto;
import com.samoilenko.onlinebookstore.dto.userdtos.UserResponseDto;
import com.samoilenko.onlinebookstore.exception.RegistrationException;
import com.samoilenko.onlinebookstore.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}