package com.example.loggerPolyakovAM;

import com.example.loggerPolyakovAM.model.Log;
import com.example.loggerPolyakovAM.repository.LogRepository;
import com.example.loggerPolyakovAM.service.LogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogServiceTest {

    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private LogService logService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetLogs() {
        List<Log> logs = new ArrayList<>();
        logs.add(new Log());
        logs.add(new Log());

        when(logRepository.findAll()).thenReturn(logs);

        List<Log> result = logService.getLogs();

        assertEquals(2, result.size());
        verify(logRepository).findAll();
    }

    @Test
    public void testSave() {
        Log log = new Log();

        logService.save(log);

        verify(logRepository).save(log);
    }
}
