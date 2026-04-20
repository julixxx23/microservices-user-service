package com.microservices.userservice.user.domain.port.in;

import com.microservices.userservice.user.domain.model.Users;

public interface SaveUserPort {
    Users save(Users users);
}
