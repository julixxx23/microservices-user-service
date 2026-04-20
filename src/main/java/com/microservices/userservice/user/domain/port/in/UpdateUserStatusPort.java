package com.microservices.userservice.user.domain.port.in;

import com.microservices.userservice.user.domain.model.Users;

public interface UpdateUserStatusPort {
    Users updateStatus(Long id, Boolean status);
}
