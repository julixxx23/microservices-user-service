package com.microservices.userservice.user.infrastructure.adapter.out.persistence.entity;

import com.microservices.userservice.user.domain.model.RolesUsers;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor
@Setter
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "full_name", nullable = false, length = 300)
    private String fullName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "username", nullable = false, length = 100)
    private String userName;

    @Column(name = "password")
    private String passWord;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "address",nullable = false, length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private RolesUsers rol;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false,  updatable = false)
    private LocalDateTime createdAt;

}
