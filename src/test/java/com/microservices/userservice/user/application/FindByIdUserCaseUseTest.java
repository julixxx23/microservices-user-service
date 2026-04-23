package com.microservices.userservice.user.application;

import com.microservices.userservice.shared.domain.exception.NotFoundException;
import com.microservices.userservice.shared.fixture.UserFixture;
import com.microservices.userservice.user.domain.exception.UserNotFoundException;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindByIdUserCaseUseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private FindByIdUserUseCase findByIdUserUseCase;

    @Test
    @DisplayName("Should correct a findById")
    void findByCorrect(){

        Users users = UserFixture.aUserWithoutId();

        when(userRepositoryPort.findById(1L)).thenReturn(Optional.of(users));
        Users result = findByIdUserUseCase.findById(1L);

        assertNotNull(result);
        assertEquals(users.getIdUser(), result.getIdUser());

        verify(userRepositoryPort, times(1)).findById(1L);
    }

    @Test
    @DisplayName(("Should exception a findById"))
    void findByFaills(){

        when(userRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class, () -> findByIdUserUseCase.findById(1L));

        verify(userRepositoryPort, times(1)).findById(1L);
    }
}
