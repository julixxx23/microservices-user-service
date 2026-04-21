package com.microservices.userservice.user.infrastructure.adapter.in.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSimpleResponse {

    private String fullName;
    private Integer age;
    private String username;
}
