package com.samoilenko.onlinebookstore.service;

import com.samoilenko.onlinebookstore.dto.usertdos.UserRegistrationRequestDto;
import com.samoilenko.onlinebookstore.dto.usertdos.UserResponseDto;
import com.samoilenko.onlinebookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
