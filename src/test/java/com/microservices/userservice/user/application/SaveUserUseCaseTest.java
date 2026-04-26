package com.microservices.userservice.user.application;

import com.microservices.userservice.shared.fixture.UserFixture;
import com.microservices.userservice.user.domain.exception.UserAlreadyExistsException;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveUserUseCaseTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private SaveUserUseCase saveUserUseCase;

    @Test
    @DisplayName("Should save a user successfully")
    void saveCorrect(){

        Users save =  UserFixture.aValidUser();

        when(passwordEncoder.encode(any())).thenReturn("password");
        when(userRepositoryPort.existsByUsername(save.getUsername())).thenReturn(false);
        when(userRepositoryPort.save(any(Users.class))).thenReturn(save);

        Users result = saveUserUseCase.save(save);

        assertNotNull(result);
        assertEquals(save.getUsername(), result.getUsername());

        verify(userRepositoryPort, times(1)).existsByUsername(save.getUsername());
        verify(userRepositoryPort, times(1)).save(any(Users.class));
    }

    @Test
    @DisplayName("Should exception faills")
    void saveFaills() {

        Users save = UserFixture.aUserWithoutId();

        when(userRepositoryPort.existsByUsername(save.getUsername())).thenReturn(true);

        UserAlreadyExistsException ex = assertThrows(UserAlreadyExistsException.class, () -> saveUserUseCase.save(save));

        assertEquals("The username is already in use", ex.getMessage());
        verify(userRepositoryPort, times(1)).existsByUsername(save.getUsername());

    }
}