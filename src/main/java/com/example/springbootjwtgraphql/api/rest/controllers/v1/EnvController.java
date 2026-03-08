package com.example.springbootjwtgraphql.api.rest.controllers.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(EnvController.class);

    @GetMapping("/env")
    public Map<String, String> env() {
        
        logger.info("INFO: Logging test to Application Insights");
        logger.warn("WARN: Logging test to Application Insights");
        logger.error("ERROR: Logging test to Application Insights");

        return Map.of("env", env);
    }
}
