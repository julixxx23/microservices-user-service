package com.microservices.userservice.user.application;

import com.microservices.userservice.shared.domain.model.PageDomain;
import com.microservices.userservice.shared.fixture.UserFixture;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListUserUseCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private ListUserUseCase listUserUseCase;

    @Test
    @DisplayName("Should correct a List")
    void findByCorrect(){

        int page = 0;
        int size = 10;

        PageDomain<Users> pageCorrect = UserFixture.aPageDomainOfUsers();
        when(userRepositoryPort.list(page, size)).thenReturn(pageCorrect);
        PageDomain<Users> result = listUserUseCase.list(page, size);

        assertNotNull(result);
        assertEquals(2, result.content().size());
        assertEquals(0, result.currentPage());
        assertEquals(1, result.totalPages());
        assertEquals(2L, result.totalElements());

        verify(userRepositoryPort, times(1)).list(page, size);
    }
    @Test
    @DisplayName("Should exception a List")
    void listFaills(){

        int page = 0;
        int size = 10;

        PageDomain<Users> pageFaills = new PageDomain<>(List.of(), 0, 0, 0L);
        when(userRepositoryPort.list(page, size)).thenReturn(pageFaills);
        PageDomain<Users> result = listUserUseCase.list(page, size);

        assertNotNull(result);
        assertTrue(result.content().isEmpty());
        assertEquals(0L, result.totalElements());

        verify(userRepositoryPort, times(1)).list(page, size);
    }


}
