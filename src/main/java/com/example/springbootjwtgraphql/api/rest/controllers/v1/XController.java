package com.example.springbootjwtgraphql.api.rest.controllers.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XController {
    Function<Integer, Integer> square = (x) -> x * x;

    int add(int a, int b) {
        return a + b;
    }

    @GetMapping("/hello")
    public ResponseEntity<List<Integer>> sayHello() {

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        list.add(3);

        System.out.println(list);
        return ResponseEntity.ok(list.stream().map(square).toList());
    }
}