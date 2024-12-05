package com.example.validatorPolyakovAM.service;

import com.example.validatorPolyakovAM.model.ValidateBody;
import com.example.validatorPolyakovAM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ValidateService {
    private final UserRepository userRepository;
    public Boolean validate(ValidateBody body) {
        String id = userRepository.getGeneratedIdByLogin(body.getLogin());
        if (id == null) {
            return false;
        }
        return id.equals(body.getId());
    }
}
