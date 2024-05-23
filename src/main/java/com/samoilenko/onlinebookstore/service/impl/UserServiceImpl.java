package com.samoilenko.onlinebookstore.service.impl;

import com.samoilenko.onlinebookstore.dto.userdtos.UserRegistrationRequestDto;
import com.samoilenko.onlinebookstore.dto.userdtos.UserResponseDto;
import com.samoilenko.onlinebookstore.exception.RegistrationException;
import com.samoilenko.onlinebookstore.exception.UserNotFoundException;
import com.samoilenko.onlinebookstore.mapper.UserMapper;
import com.samoilenko.onlinebookstore.model.Role;
import com.samoilenko.onlinebookstore.model.ShoppingCart;
import com.samoilenko.onlinebookstore.model.User;
import com.samoilenko.onlinebookstore.repository.RoleRepository;
import com.samoilenko.onlinebookstore.repository.ShoppingCartRepository;
import com.samoilenko.onlinebookstore.repository.UserRepository;
import com.samoilenko.onlinebookstore.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException("User with this email already exist");
        }
        User newUser = userMapper.toModel(requestDto);
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        newUser.setRoles(Set.of(roleRepository.findByName(Role.RoleName.USER)));
        User savedUser = userRepository.save(newUser);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(savedUser);
        shoppingCartRepository.save(shoppingCart); //TODO add to shoping cart service
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String email = ((UserDetails) principal).getUsername();
                return userRepository.findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
            }
        }
        throw new RuntimeException("No authenticated user found");
    }
}
