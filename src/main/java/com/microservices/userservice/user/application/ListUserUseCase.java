package com.microservices.userservice.user.application;

import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.ListUsersPort;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ListUserUseCase implements ListUsersPort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public PageDomain<Users> list(int page, int size){
        return userRepositoryPort.list(page, size);
    }
}
