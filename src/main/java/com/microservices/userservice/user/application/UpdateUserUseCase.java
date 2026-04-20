package com.microservices.userservice.user.application;

import com.microservices.userservice.user.domain.exception.UserAlreadyExistsException;
import com.microservices.userservice.user.domain.exception.UserNotFoundException;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.UpdateUserPort;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserUseCase implements UpdateUserPort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Users update(Long id, Users users){
        userRepositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if(userRepositoryPort.existsByUsernameExcludingId(users.getUsername(), id)){
                throw new UserAlreadyExistsException("The username is already in use");
        }
        return userRepositoryPort.update(id, users);
    }
}
