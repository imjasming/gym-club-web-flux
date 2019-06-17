package com.gymclub.api.controller;

import com.gymclub.api.annotation.RateLimitAspect;
import com.gymclub.api.dto.hateoas.Greeting;
import com.gymclub.api.service.DataService;
import com.gymclub.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class HateoasHandler {


    @Autowired
    private DataService dataService;
    @Autowired
    private UserService userService;


    private static final String TEMPLATE = "Hello, %s!";

    @RateLimitAspect(permitsPerSecond = 1)
    public Mono<ServerResponse> greeting(ServerRequest request) {
        String name = request.queryParam("name").isPresent() ? request.queryParam("name").get() : "World";
        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(HateoasHandler.class).greeting(request)).withSelfRel());

        return ServerResponse.ok().body(Mono.just(greeting), Greeting.class);
    }
}
