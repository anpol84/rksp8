package com.example.loggerPolyakovAM.service;

import com.example.loggerPolyakovAM.model.Log;
import com.example.loggerPolyakovAM.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    public List<Log> getLogs() {
        return logRepository.findAll();
    }

    public void save(Log log) {
        logRepository.save(log);
    }
}
