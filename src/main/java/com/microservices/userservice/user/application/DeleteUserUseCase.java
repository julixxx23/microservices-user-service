package com.microservices.userservice.user.application;

import com.microservices.userservice.user.domain.exception.UserNotFoundException;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.DeleteUserPort;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserUseCase implements DeleteUserPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void delete(Long id){
        userRepositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepositoryPort.delete(id);


    }
}
