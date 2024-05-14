package com.samoilenko.onlinebookstore.mapper;

import com.samoilenko.onlinebookstore.config.MapperConfig;
import com.samoilenko.onlinebookstore.dto.UserRegistrationRequestDto;
import com.samoilenko.onlinebookstore.dto.UserResponseDto;
import com.samoilenko.onlinebookstore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    UserResponseDto toResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    User toModel(UserRegistrationRequestDto requestDto);

}
