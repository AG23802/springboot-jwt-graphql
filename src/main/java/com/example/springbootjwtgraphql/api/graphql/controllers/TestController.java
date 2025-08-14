package com.example.springbootjwtgraphql.api.graphql.controllers;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    @QueryMapping
    public String hello() {
        return "Hello GraphQL!";
    }
}
