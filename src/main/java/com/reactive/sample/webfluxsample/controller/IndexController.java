package com.reactive.sample.webfluxsample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {
    @GetMapping("/index")
    public Mono<String> getIndex() {
        return Mono.just("Hello World!");
    }
}
