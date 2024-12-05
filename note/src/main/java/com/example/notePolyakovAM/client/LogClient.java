package com.example.notePolyakovAM.client;

import com.example.notePolyakovAM.model.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "log")
public interface LogClient {

    @PostMapping("/logs")
    void save(@RequestBody Log log, @RequestHeader(HttpHeaders.COOKIE) String cookie);
}
