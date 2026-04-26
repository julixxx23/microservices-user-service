package com.microservices.userservice.user.infrastructure.adapter.in.rest.dto;

import com.microservices.userservice.user.domain.model.RolesUsers;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "Full name is required")
    @Size(max = 250, message = "Full name must not exceed 250 characters")
    private String fullName;

    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username must not exceed 100 characters")
    private String username;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "User role is required")
    private RolesUsers role;
}