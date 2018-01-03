package com.reactive.sample.webfluxsample.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Authenticator {
    @Value("${configuration.api.access-key}")
    private String apiKey;

    public Mono<ServerResponse> filterRoute(ServerRequest request, HandlerFunction<ServerResponse> handlerFunction){
        if (request.headers().asHttpHeaders().containsKey("x-api-key")) {
            if (request.headers().asHttpHeaders().get("x-api-key").get(0).equals(apiKey)) {
                return handlerFunction.handle(request);
            }
            return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ServerResponse.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
