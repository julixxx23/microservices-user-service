package com.microservices.userservice.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private Long idUsers;
    private String lastName;
    private Integer age;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private RolesUsers role;
    private Boolean is_Active;
    private LocalDateTime created_At;
}
