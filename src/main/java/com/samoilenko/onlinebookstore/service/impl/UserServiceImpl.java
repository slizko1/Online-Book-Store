package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.UserRegistrationRequestDto;
import com.samoilenko.onlinebookstore.dto.UserResponseDto;
import com.samoilenko.onlinebookstore.exception.RegistrationException;
import com.samoilenko.onlinebookstore.mapper.UserMapper;
import com.samoilenko.onlinebookstore.model.User;
import com.samoilenko.onlinebookstore.repository.UserRepository;
import com.samoilenko.onlinebookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("User with this email already exist");
        }
        User savedUser = userMapper.toModel(requestDto);
        return userMapper.toResponseDto(userRepository.save(savedUser));
    }
}
