package com.microservices.userservice.user.application;

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
public class UpdateUserUseCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private UpdateUserUseCase updateUserUseCase;

    @Test
    @DisplayName("Should update a user successfully")
    void updateCorrect(){

        Users entrada = UserFixture.aUserWithoutId();
        Users update = UserFixture.aValidUser();

        when(userRepositoryPort.findById(1L)).thenReturn(Optional.of(update));
        when(userRepositoryPort.existsByUsernameExcludingId(update.getUsername(), 1L))
                .thenReturn(false);
        when(userRepositoryPort.update(eq(1L), any(Users.class))).thenReturn(update);

        Users result = updateUserUseCase.update(1L, entrada);

        assertNotNull(result);
        assertEquals(update.getUsername(), result.getUsername());

        verify(userRepositoryPort, times(1)).findById(1L);
        verify(userRepositoryPort, times(1)).existsByUsernameExcludingId(entrada.getUsername(), 1L);
        verify(userRepositoryPort, times(1)).update(eq(1L), any(Users.class));
    }

    @Test
    @DisplayName("Should exception a faills update")
    void updateFaills(){

        Users update = UserFixture.aUserWithoutId();
        when(userRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class, () -> updateUserUseCase.update(1L, update));

        verify(userRepositoryPort, times(1)).findById(1L);
        verify(userRepositoryPort, never()).update(eq(1L), any());
    }
}
