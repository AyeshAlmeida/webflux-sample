package com.reactive.sample.webfluxsample.hanlder;

import com.reactive.sample.webfluxsample.model.RequestBody;
import com.reactive.sample.webfluxsample.model.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;


@Component
public class SampleHandler {
    public Mono<ServerResponse> handleRequest(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just("Sample Route Successful!"), String.class);
    }

    public Mono<ServerResponse> handleNestedRoute(ServerRequest request) {
        return ServerResponse.ok().body(fromObject("Nested Route Handled Successfully"));
    }

    public Mono<ServerResponse> handleRequestWithABody(ServerRequest request) {
        return request
                .bodyToMono(RequestBody.class)
                .flatMap(requestBody -> {
                    ResponseBody responseBody = new ResponseBody();
                    responseBody.setStatus("Success");
                    responseBody.setDescription("Successfully Handled the request");
                    responseBody.setRequestBody(requestBody);
                    return ServerResponse.ok().body(fromObject(responseBody));
                })
                .switchIfEmpty(
                        ServerResponse.status(HttpStatus.BAD_REQUEST).build());
    }
}
