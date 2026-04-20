package com.microservices.userservice.user.domain.port.in;

import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.user.domain.model.Users;

import java.util.Optional;

public interface ListUsersPort {
    PageDomain<Users>  list(int page, int size);

}
