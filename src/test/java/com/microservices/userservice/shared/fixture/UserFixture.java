package com.microservices.userservice.shared.fixture;


import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.user.domain.model.RolesUsers;
import com.microservices.userservice.user.domain.model.Users;

import java.util.List;

public class UserFixture {

    public static Users aValidUser() {
        return Users.builder()
                .idUser(1L)
                .username("juanitaOdon")
                .password("kfje0rrer")
                .fullName("Juana Cristina Baltazar")
                .age(23)
                .phoneNumber("39303344")
                .address("San Nicolas Zone 4 Mixco")
                .role(RolesUsers.ADMIN)
                .isActive(true)
                .build();
    }

    public static Users aUserWithoutId() {
        return Users.builder()
                .username("juanitaOdon")
                .password("kfje0rrer")
                .fullName("Juana Cristina Baltazar")
                .age(23)
                .phoneNumber("39303344")
                .address("San Nicolas Zone 4 Mixco")
                .role(RolesUsers.ADMIN)
                .isActive(true)
                .build();
    }

    public static Users aUserInactive(){
        return Users.builder()
                .idUser(1L)
                .username("juanitaOdon")
                .password("kfje0rrer")
                .fullName("Juana Cristina Baltazar")
                .age(23)
                .phoneNumber("39303344")
                .address("San Nicolas Zone 4 Mixco")
                .role(RolesUsers.ADMIN)
                .isActive(false)
                .build();

    }

    public static List<Users> aUserList() {
        return List.of(aValidUser(), Users.builder()
                .idUser(1L)
                .username("juanitaOdon")
                .password("kfje0rrer")
                .fullName("Juana Cristina Baltazar")
                .age(23)
                .phoneNumber("39303344")
                .address("San Nicolas Zone 4 Mixco")
                .role(RolesUsers.ADMIN)
                .isActive(true)
                .build());
    }

    public static PageDomain<Users> aPageDomainOfUsers() {
        return new PageDomain<>(aUserList(), 0, 1, 2L);
    }
}