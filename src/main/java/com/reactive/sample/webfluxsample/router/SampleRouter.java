package com.reactive.sample.webfluxsample.router;

import com.reactive.sample.webfluxsample.hanlder.SampleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SampleRouter {
    @Bean
    public RouterFunction<ServerResponse> sampleRoute(SampleHandler handler) {
        return route(GET("/simple-route"), handler::handleRequest);
    }
}
