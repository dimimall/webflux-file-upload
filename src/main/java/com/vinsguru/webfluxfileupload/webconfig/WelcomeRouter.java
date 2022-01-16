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

//functional style programming model with lambda-based
//leaves application to handle all requests
//route functions all requests update delete post get for Cart product and user
@Configuration
public class WelcomeRouter {
    @Bean
    public RouterFunction<ServerResponse> route(WelcomeHandler welcomeHandler) {
        return RouterFunctions
                .route(POST("/api/create")
                                .and(accept(APPLICATION_JSON)),welcomeHandler::addNewProduct)
                .andRoute(GET("/api/products/{id:[0-9]+}")
                                .and(accept(APPLICATION_JSON)), welcomeHandler::getProduct)
                .andRoute(GET("/api/products/")
                        .and(accept(APPLICATION_JSON)), welcomeHandler::listProducts)
                .andRoute(GET("/add/redirect"), req ->
                        ServerResponse.temporaryRedirect(URI.create("/product"))
                                .build())
                .andRoute(PUT("/api/upload/{id:[0-9]+}").and(accept(APPLICATION_JSON)),welcomeHandler::updateProduct)
                .andRoute(DELETE("/api/delete/{id:[0-9]+}").and(accept(APPLICATION_JSON)),welcomeHandler::deleteProduct)
                .andRoute(GET("/api/users/{id:[0-9]+}")
                        .and(accept(APPLICATION_JSON)), welcomeHandler::getUser)
                .andRoute(GET("/api/users/{id:[0-9]+}/{password}")
                        .and(accept(APPLICATION_JSON)), welcomeHandler::getUserByIdPassword)
                .andRoute(PUT("/api/upload/user/{id:[0-9]+}").and(accept(APPLICATION_JSON)),welcomeHandler::updateUser)
                .andRoute(POST("/api/create/user")
                        .and(accept(APPLICATION_JSON)),welcomeHandler::addNewUser)
                .andRoute(GET("/api/users/")
                        .and(accept(APPLICATION_JSON)), welcomeHandler::listUsers)
                .andRoute(DELETE("/api/delete/user/{id:[0-9]+}").and(accept(APPLICATION_JSON)),welcomeHandler::deleteUser)
                .andRoute(GET("/api/cart/{id:[0-9]+}")
                        .and(accept(APPLICATION_JSON)), welcomeHandler::getCart)
                .andRoute(PUT("/api/upload/cart/{id:[0-9]+}").and(accept(APPLICATION_JSON)),welcomeHandler::updateCart)
                .andRoute(POST("/api/create/cart")
                        .and(accept(APPLICATION_JSON)),welcomeHandler::addNewCart)
                .andRoute(DELETE("/api/delete/cart/{id:[0-9]+}").and(accept(APPLICATION_JSON)),welcomeHandler::deleteCart);
    }

}
