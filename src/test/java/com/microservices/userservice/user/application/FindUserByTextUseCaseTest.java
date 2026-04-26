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
public class FindUserByTextUseCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private FindUserByTextUseCase findUserByTextUseCase;

    @Test
    @DisplayName("Should correct a text")
    void textCorrecto(){

        String text = "Product";
        int page = 0;
        int size = 10;

        PageDomain<Users> pageCorrect = UserFixture.aPageDomainOfUsers();
        when(userRepositoryPort.findByText(text, page, size)).thenReturn(pageCorrect);
        PageDomain<Users> result = findUserByTextUseCase.findByText(text, page, size);

        assertNotNull(result);
        assertEquals(2, result.content().size());
        assertEquals(0, result.currentPage());
        assertEquals(1, result.totalPages());
        assertEquals(2L, result.totalElements());

        verify(userRepositoryPort, times(1)).findByText(text, page, size);
    }

    @Test
    @DisplayName("Should exception a findyBy")
    void findByFaills(){

        String text = "Product";
        int page = 0;
        int size = 10;

        PageDomain<Users> pageNoCorrect = new PageDomain<>(List.of(), 0, 0, 0L);
        when(userRepositoryPort.findByText(text, page, size)).thenReturn(pageNoCorrect);
        PageDomain<Users> result = findUserByTextUseCase.findByText(text, page, size);

        assertNotNull(result);
        assertTrue(result.content().isEmpty());
        assertEquals(0L, result.totalElements());

        verify(userRepositoryPort, times(1)).findByText(text, page, size);


    }
}
