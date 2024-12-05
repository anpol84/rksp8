package com.example.loggerPolyakovAM.client;

import com.example.loggerPolyakovAM.model.ValidateBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "validator")
public interface ValidatorClient {

    @PostMapping("/validate")
    Boolean validate(@RequestBody ValidateBody validateBody);
}

