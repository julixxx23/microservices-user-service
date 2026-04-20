package com.microservices.userservice.user.application;

import com.microservices.userservice.user.domain.exception.UserNotFoundException;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.FindUserByIdPort;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindByIdUserUseCase implements FindUserByIdPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Users findById(Long id){
        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }
}
