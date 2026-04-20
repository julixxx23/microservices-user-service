package com.microservices.userservice.user.domain.port.out;

import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.user.domain.model.Users;

import java.util.Optional;

public interface UserRepositoryPort {

    Users save(Users users);
    Users update(Long id, Users users);
    Optional<Users> findById(Long id);
    PageDomain<Users> findByText(String text, int page, int size);
    PageDomain<Users> list(int page, int size);
    void delete(Long id);
    Users updateStatus(Long id, Boolean status);
    boolean existsByUsername(String username);
    boolean existsByUsernameExcludingId(String username, Long id);


}
