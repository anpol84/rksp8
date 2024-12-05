package com.example.validatorPolyakovAM.repository;

import com.example.validatorPolyakovAM.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT generated_id FROM users WHERE login = :login", nativeQuery = true)
    String getGeneratedIdByLogin(String login);
}
