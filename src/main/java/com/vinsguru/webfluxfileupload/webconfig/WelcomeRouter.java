package com.vinsguru.webfluxfileupload.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class WelcomeRouter {
    @Bean
    public RouterFunction<ServerResponse> route(WelcomeHandler welcomeHandler) {
        return RouterFunctions
                .route(POST("/index")
                                .and(accept(APPLICATION_JSON)),welcomeHandler::addNewProduct)
                .andRoute(GET("/second/redirect"), req ->
                        ServerResponse.temporaryRedirect(URI.create("/second"))
                                .build());
    }

}
