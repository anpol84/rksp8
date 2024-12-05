package com.example.authServerPolyakovAM;

import com.example.authServerPolyakovAM.model.User;
import com.example.authServerPolyakovAM.repository.UserRepository;
import com.example.authServerPolyakovAM.service.UniqueStringGenerator;
import com.example.authServerPolyakovAM.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UniqueStringGenerator uniqueStringGenerator;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetIdUserNotFound() {
        String login = "testLogin";
        String generatedId = "uniqueId123";

        when(userRepository.findByLogin(login)).thenReturn(Optional.empty());
        when(uniqueStringGenerator.generateUniqueString(login)).thenReturn(generatedId);

        String result = userService.getId(login);

        assertEquals(generatedId, result);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testGetIdUserFound() {
        String login = "existingLogin";
        String generatedId = "existingId123";

        User existingUser = new User();
        existingUser.setLogin(login);
        existingUser.setGeneratedId(generatedId);

        when(userRepository.findByLogin(login)).thenReturn(Optional.of(existingUser));

        String result = userService.getId(login);

        assertEquals(generatedId, result);
        verify(userRepository, never()).save(any(User.class));
    }
}
