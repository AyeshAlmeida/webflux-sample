package com.reactive.sample.webfluxsample.router;

import com.reactive.sample.webfluxsample.hanlder.SampleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterWithContext {
    @Bean
    public RouterFunction<ServerResponse> routeWithContext(SampleHandler handler) {
        return nest(path("/context"),
                sampleRoute(handler)
                        .and(handleRequestBodyRoute(handler)));
    }

    private RouterFunction<ServerResponse> sampleRoute(SampleHandler handler) {
        return route(GET("/sample"), handler::handleNestedRoute);
    }

    private RouterFunction<ServerResponse> handleRequestBodyRoute(SampleHandler handler) {
        return route(POST("/echo").and(accept(MediaType.APPLICATION_JSON)), handler::handleRequestWithABody);
    }
}
