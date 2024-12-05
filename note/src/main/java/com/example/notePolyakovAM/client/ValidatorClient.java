package com.example.notePolyakovAM.client;

import com.example.notePolyakovAM.model.ValidateBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "validator")
public interface ValidatorClient {

    @PostMapping("/validate")
    Boolean validate(@RequestBody ValidateBody validateBody);
}
