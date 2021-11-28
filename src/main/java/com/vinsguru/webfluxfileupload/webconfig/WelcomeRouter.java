package com.vinsguru.webfluxfileupload.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class WelcomeRouter {
    @Bean
    public RouterFunction<ServerResponse> route(WelcomeHandler welcomeHandler) {
        return RouterFunctions
                .route(POST("/api/create")
                                .and(accept(APPLICATION_JSON)),welcomeHandler::addNewProduct)
                .andRoute(GET("/products/{id:[0-9]+}")
                                .and(accept(APPLICATION_JSON)), welcomeHandler::getProduct)
                .andRoute(GET("/products/")
                        .and(accept(APPLICATION_JSON)), welcomeHandler::listProducts)
                .andRoute(GET("/add/redirect"), req ->
                        ServerResponse.temporaryRedirect(URI.create("/product"))
                                .build())
                .andRoute(PUT("/api/upload/{id:[0-9]+}").and(accept(APPLICATION_JSON)),welcomeHandler::updateProduct)
                .andRoute(DELETE("/api/delete/{id:[0-9]+}").and(accept(APPLICATION_JSON)),welcomeHandler::deleteProduct);
    }

}
