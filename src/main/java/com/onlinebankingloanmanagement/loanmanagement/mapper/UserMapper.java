package com.onlinebankingloanmanagement.loanmanagement.mapper;

import com.onlinebankingloanmanagement.loanmanagement.dto.UserRegisterRequest;
import com.onlinebankingloanmanagement.loanmanagement.dto.UserResponse;
import com.onlinebankingloanmanagement.loanmanagement.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity User and its DTOs.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDto(User user);

    @Mapping(target = "password", ignore = true) // Password will be encoded in the service
    User toEntity(UserRegisterRequest userRegisterRequest);
}