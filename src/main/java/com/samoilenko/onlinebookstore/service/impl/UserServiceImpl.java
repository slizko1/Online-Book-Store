package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.userdtos.UserRegistrationRequestDto;
import com.samoilenko.onlinebookstore.dto.userdtos.UserResponseDto;
import com.samoilenko.onlinebookstore.exception.RegistrationException;
import com.samoilenko.onlinebookstore.mapper.UserMapper;
import com.samoilenko.onlinebookstore.model.Role;
import com.samoilenko.onlinebookstore.model.User;
import com.samoilenko.onlinebookstore.repository.RoleRepository;
import com.samoilenko.onlinebookstore.repository.UserRepository;
import com.samoilenko.onlinebookstore.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("User with this email already exist");
        }
        User savedUser = userMapper.toModel(requestDto);
        savedUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        savedUser.setRoles(Set.of(roleRepository.findByName(Role.RoleName.USER)));
        return userMapper.toResponseDto(userRepository.save(savedUser));
    }
}
