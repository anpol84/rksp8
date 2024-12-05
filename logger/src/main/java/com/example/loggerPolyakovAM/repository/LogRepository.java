package com.example.loggerPolyakovAM.repository;

import com.example.loggerPolyakovAM.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
