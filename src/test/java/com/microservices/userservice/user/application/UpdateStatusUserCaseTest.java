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
public class UpdateStatusUserCaseTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private UpdateStatusUserUseCase updateStatusUserUseCase;

    @Test
    @DisplayName("Should udpdate status correct")
    void updateCorrect(){

        Users valid = UserFixture.aValidUser();
        Users inactive = UserFixture.aUserInactive();

        when(userRepositoryPort.findById(1L)).thenReturn(Optional.of(valid));
        when(userRepositoryPort.updateStatus(1L, false)).thenReturn(inactive);

        Users result = updateStatusUserUseCase.updateStatus(1L, false);

        assertNotNull(result);
        assertEquals(false, result.getIsActive());

        verify(userRepositoryPort, times(1)).findById(1L);
        verify(userRepositoryPort, times(1)).updateStatus(1L, false);
    }

    @Test
    @DisplayName("Should exception a updateStatus")
    void updateFails(){

        Users inactive = UserFixture.aUserWithoutId();

        assertThrows(
                UserNotFoundException.class, () -> updateStatusUserUseCase.updateStatus(1L, false));
        verify(userRepositoryPort, times(1)).findById(1L);
        verify(userRepositoryPort, never()).updateStatus(any(), any());

    }

}
