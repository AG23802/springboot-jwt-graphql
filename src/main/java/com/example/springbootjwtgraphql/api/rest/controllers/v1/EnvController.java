package com.example.springbootjwtgraphql.api.rest.controllers.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class EnvController {
    @Value("${spring.profiles.active}")
    private String env;

    @GetMapping("/env")
    public Map<String, String> env() {
        return Map.of("env", env);
    }
}
