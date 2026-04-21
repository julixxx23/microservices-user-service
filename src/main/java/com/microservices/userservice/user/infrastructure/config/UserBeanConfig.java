package com.microservices.userservice.user.infrastructure.config;

import com.microservices.userservice.user.application.*;
import com.microservices.userservice.user.domain.port.in.*;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import com.microservices.userservice.user.infrastructure.adapter.out.persistence.UserJpaAdapter;
import com.microservices.userservice.user.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.microservices.userservice.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserBeanConfig {

    @Bean
    public UserRepositoryPort userRepositoryPort(
            UserJpaRepository jpaRepository,
            UserPersistenceMapper mapper){
        return new UserJpaAdapter(jpaRepository, mapper);
    }


    @Bean
    public SaveUserPort saveUserPort(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder){
        return new SaveUserUseCase(userRepositoryPort, passwordEncoder);
    }

    @Bean
    public UpdateUserPort updateUserPort(UserRepositoryPort userRepositoryPort){
        return new UpdateUserUseCase(userRepositoryPort);
    }

    @Bean
    public FindUserByIdPort findUserByIdPort(UserRepositoryPort userRepositoryPort){
        return new FindByIdUserUseCase(userRepositoryPort);
    }

    @Bean
    public FindUsersByTextPort findUsersByTextPort(UserRepositoryPort userRepositoryPort){
        return new FindUserByTextUseCase(userRepositoryPort);
    }

    @Bean
    public ListUsersPort listUsersPort(UserRepositoryPort userRepositoryPort){
        return new ListUserUseCase(userRepositoryPort);
    }

    @Bean
    public DeleteUserPort deleteUserPort(UserRepositoryPort userRepositoryPort){
        return new DeleteUserUseCase(userRepositoryPort);
    }

    @Bean
    public UpdateUserStatusPort updateUserStatusPort(UserRepositoryPort userRepositoryPort){
        return new UpdateStatusUserUseCase(userRepositoryPort);
    }



}
