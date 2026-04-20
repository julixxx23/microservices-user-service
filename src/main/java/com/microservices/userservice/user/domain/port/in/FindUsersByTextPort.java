package com.microservices.userservice.user.domain.port.in;

import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.user.domain.model.Users;

public interface FindUsersByTextPort {
    PageDomain<Users> findByText(String text, int page, int size);
}
