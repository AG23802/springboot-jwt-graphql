package com.example.springbootjwtgraphql.api.rest.controllers.v1;

public record Fruit(
        Integer id,
        String name,
        String emoji,
        String color,
        Boolean isSweet,
        Integer stock,
        Double price,
        String details
) {}
