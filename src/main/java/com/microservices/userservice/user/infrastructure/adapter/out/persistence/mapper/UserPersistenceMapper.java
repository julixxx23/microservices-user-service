package com.microservices.userservice.user.infrastructure.adapter.out.persistence.mapper;

import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.infrastructure.adapter.out.persistence.entity.UsersEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public Users toDomain(UsersEntity entity){
        return Users.builder()
                .idUser(entity.getIdUser())
                .username(entity.getUserName())
                .fullName(entity.getFullName())
                .age(entity.getAge())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .role(entity.getRol())
                .isActive(entity.getIsActive())
                .password(entity.getPassWord())
                .createdAt(entity.getCreatedAt())
                .build();

    }

    public UsersEntity toEntity(Users users){
        UsersEntity entity = new UsersEntity();
        entity.setIdUser(users.getIdUser());
        entity.setFullName(users.getFullName());
        entity.setUserName(users.getUsername());
        entity.setAddress(users.getAddress());
        entity.setPhoneNumber(users.getPhoneNumber());
        entity.setAge(users.getAge());
        entity.setRol(users.getRole());
        entity.setPassWord(users.getPassword());
        entity.setIsActive(users.getIsActive());
        entity.setCreatedAt(users.getCreatedAt());
        return entity;
    }

}
