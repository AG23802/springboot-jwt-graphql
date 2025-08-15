package com.example.springbootjwtgraphql.api.graphql.controllers;

import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.example.springbootjwtgraphql.domain.entities.City;
import reactor.core.publisher.Sinks;

@Controller
public class CitySubscriptionController {
    private final Sinks.Many<City> citySink = Sinks.many().multicast().onBackpressureBuffer();

    @SubscriptionMapping
    public Publisher<City> cities() {
        return citySink.asFlux();
    }

    public void publishCity(City city) {
        citySink.tryEmitNext(city);
    }
}
