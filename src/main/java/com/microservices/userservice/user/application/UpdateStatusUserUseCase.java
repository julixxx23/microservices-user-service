package com.microservices.userservice.user.application;

import com.microservices.userservice.user.domain.exception.UserNotFoundException;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.UpdateUserStatusPort;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateStatusUserUseCase implements UpdateUserStatusPort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Users updateStatus(Long id, Boolean status){
        userRepositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userRepositoryPort.updateStatus(id, status);
    }
}
