package com.microservices.userservice.user.application;

import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.FindUsersByTextPort;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindUserByTextUseCase implements FindUsersByTextPort {

    private UserRepositoryPort userRepositoryPort;

    @Override
    public PageDomain<Users> findByText(String text, int page, int size){
        return userRepositoryPort.findByText(text, page, size);
    }
}
