package com.reactive.sample.webfluxsample.hanlder;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class SampleHandler {
    public Mono<ServerResponse> handleRequest(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("Sample Route Successful!"), String.class);
    }
}
