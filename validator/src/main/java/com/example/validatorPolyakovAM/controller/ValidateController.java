package com.example.validatorPolyakovAM.controller;

import com.example.validatorPolyakovAM.model.ValidateBody;
import com.example.validatorPolyakovAM.service.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/validate")
@RequiredArgsConstructor
public class ValidateController {
    private final ValidateService validateService;

    @PostMapping
    Boolean validateUser(@RequestBody ValidateBody user) {
        return validateService.validate(user);
    }
}
