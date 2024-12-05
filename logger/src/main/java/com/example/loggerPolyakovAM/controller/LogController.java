package com.example.loggerPolyakovAM.controller;

import com.example.loggerPolyakovAM.client.ValidatorClient;
import com.example.loggerPolyakovAM.model.Log;
import com.example.loggerPolyakovAM.model.ValidateBody;
import com.example.loggerPolyakovAM.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;
    private final ValidatorClient validatorClient;

    @GetMapping
    public ResponseEntity<?> getLogs(@CookieValue(value = "id", required = false) String id,
                                     @CookieValue(value = "login", required = false) String login) {
        if (id == null || login == null) {
            return ResponseEntity.ok("Авторизуйтесь в основном приложении");
        }
        if (!login.equals("anpol84") || !validatorClient.validate(new ValidateBody(login, id))) {
            return ResponseEntity.ok("Вы не anpol84");
        }
        return ResponseEntity.ok(logService.getLogs());
    }

    @PostMapping
    public void saveLog(@RequestBody Log log, @CookieValue(value = "id", required = false) String id,
                        @CookieValue(value = "login", required = false) String login) {
        if (id == null || login == null) {
            return;
        }
        if (!login.equals("anpol84") || !validatorClient.validate(new ValidateBody(login, id))) {
            return;
        }
        logService.save(log);
    }
}
