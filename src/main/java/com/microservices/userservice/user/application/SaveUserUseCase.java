package com.microservices.userservice.user.application;

import com.microservices.userservice.user.domain.exception.UserAlreadyExistsException;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.SaveUserPort;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class SaveUserUseCase implements SaveUserPort {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users save(Users users){
        if(userRepositoryPort.existsByUsername(users.getUsername())){
            throw new UserAlreadyExistsException("The username is already in use");
        }

        Users userHash = Users.builder()
                .idUsers(users.getIdUsers())
                .lastName(users.getLastName())
                .address(users.getAddress())
                .age(users.getAge())
                .phoneNumber(users.getPhoneNumber())
                .role(users.getRole())
                .is_Active(true)
                .username(users.getUsername())
                .password(passwordEncoder.encode(users.getPassword()))
                .build();

        return userRepositoryPort.save(userHash);
    }


}
