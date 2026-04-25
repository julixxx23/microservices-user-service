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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private DeleteUserUseCase deleteUserUseCase;

    @Test
    @DisplayName("Shoudl exit delete")
    void  deleteExit(){

        Users users  = UserFixture.aUserWithoutId();

        when(userRepositoryPort.findById(1L)).thenReturn(Optional.of(users));

        deleteUserUseCase.delete(1L);
        verify(userRepositoryPort, times(1)).delete(1L);
    }

    @Test
    @DisplayName("Should exception a delete")
    void deleteFaills() {

        when(userRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class, () -> deleteUserUseCase.delete(1L));
        verify(userRepositoryPort, never()).delete(any());

    }


}
