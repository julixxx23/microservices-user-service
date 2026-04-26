package com.microservices.userservice.user.infrastructure.adapter.in.rest.mapper.rest;

import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.dto.UserCreateRequest;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.dto.UserResponse;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.dto.UserUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapperRest {

    public Users toDomain(UserCreateRequest request){
        return Users.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .age(request.getAge())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }

    public Users toDomain(UserUpdateRequest request){
        return Users.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .age(request.getAge())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

    }

    public UserResponse toResponse(Users users){
        return UserResponse.builder()
                .idUser(users.getIdUser())
                .fullName(users.getFullName())
                .username(users.getUsername())
                .address(users.getAddress())
                .phoneNumber(users.getPhoneNumber())
                .age(users.getAge())
                .role(users.getRole())
                .build();

    }
}
