package com.microservices.userservice.user.infrastructure.adapter.in.rest.dto;

import com.microservices.userservice.user.domain.model.RolesUsers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long idUser;
    private String fullName;
    private Integer age;
    private String username;
    private String phoneNumber;
    private String address;
    private RolesUsers role;
    private Boolean is_Active;
    private LocalDateTime created_At;
}
