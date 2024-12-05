package com.example.validatorPolyakovAM;

import com.example.validatorPolyakovAM.model.ValidateBody;
import com.example.validatorPolyakovAM.repository.UserRepository;
import com.example.validatorPolyakovAM.service.ValidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ValidateServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ValidateService validateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testValidateUserNotFound() {
        ValidateBody body = new ValidateBody("testLogin", "someId");

        when(userRepository.getGeneratedIdByLogin(body.getLogin())).thenReturn(null);

        Boolean result = validateService.validate(body);

        assertFalse(result);
        verify(userRepository).getGeneratedIdByLogin(body.getLogin());
    }

    @Test
    public void testValidateIdDoesNotMatch() {
        ValidateBody body = new ValidateBody("testLogin", "wrongId");
        String generatedId = "correctId";

        when(userRepository.getGeneratedIdByLogin(body.getLogin())).thenReturn(generatedId);

        Boolean result = validateService.validate(body);

        assertFalse(result);
        verify(userRepository).getGeneratedIdByLogin(body.getLogin());
    }

    @Test
    public void testValidateIdMatches() {
        ValidateBody body = new ValidateBody("testLogin", "correctId");
        String generatedId = "correctId";

        when(userRepository.getGeneratedIdByLogin(body.getLogin())).thenReturn(generatedId);

        Boolean result = validateService.validate(body);

        assertTrue(result);
        verify(userRepository).getGeneratedIdByLogin(body.getLogin());
    }
}
