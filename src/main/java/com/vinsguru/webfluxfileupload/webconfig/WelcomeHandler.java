package com.vinsguru.webfluxfileupload.webconfig;

import com.vinsguru.webfluxfileupload.Models.Cart;
import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.Models.User;
import com.vinsguru.webfluxfileupload.services.RestApiServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

//functional style programming model with lambda-based
//leaves application to handle all requests
//Handle functions get put delete post for Cart product and user
@Component
@Slf4j
public class WelcomeHandler {

    @Autowired
    private final RestApiServices restApiServices;

    public WelcomeHandler(RestApiServices restApiServices) {
        this.restApiServices = restApiServices;
    }

    public Mono<ServerResponse> listProducts(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(restApiServices.getAll(), Product.class);
    }

    public Mono<ServerResponse> getProduct(ServerRequest serverRequest) {
        Mono<Product> studentMono = restApiServices.getProduct(
                Long.parseLong(serverRequest.pathVariable("id")));
        return studentMono.flatMap(student -> ServerResponse.ok()
                        .body(fromValue(student)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addNewProduct(ServerRequest serverRequest) {
        Mono<Product> productMono = serverRequest.bodyToMono(Product.class);
        return productMono.flatMap(product ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(restApiServices.newProduct(product), Product.class));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
        final long id = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<Product> productMono = serverRequest.bodyToMono(Product.class);

        return productMono.flatMap(product ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(restApiServices.updateProduct(id,product), Product.class));
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {
        final long id= Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.noContent().build(restApiServices.deleteProduct(id))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        Mono<User> userMono = restApiServices.getUser(
                Long.parseLong(serverRequest.pathVariable("id")));
        return userMono.flatMap(user -> ServerResponse.ok()
                .body(fromValue(user)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getUserByIdPassword(ServerRequest serverRequest) {
        Mono<User> userMono = restApiServices.getUserByIdPassword(
                Long.parseLong(serverRequest.pathVariable("id")),serverRequest.pathVariable("password"));
        return userMono.flatMap(user -> ServerResponse.ok()
                .body(fromValue(user)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addNewUser(ServerRequest serverRequest) {
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return userMono.flatMap(user ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(restApiServices.newUser(user), User.class));
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        final long id = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<User> userMono = serverRequest.bodyToMono(User.class);

        return userMono.flatMap(user ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(restApiServices.updateUser(id,user), User.class));
    }

    public Mono<ServerResponse> listUsers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(restApiServices.getAllUser(), User.class);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        final long id= Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.noContent().build(restApiServices.deleteUser(id))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getCart(ServerRequest serverRequest) {
        final String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(restApiServices.getCartById(Long.parseLong(id)),Cart.class);
    }

    public Mono<ServerResponse> addNewCart(ServerRequest serverRequest) {
        Mono<Cart> cartMono = serverRequest.bodyToMono(Cart.class);
        return cartMono.flatMap(cart ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(restApiServices.newCart(cart), Cart.class));
    }

    public Mono<ServerResponse> updateCart(ServerRequest serverRequest) {
        final long id = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<Cart> cartMono = serverRequest.bodyToMono(Cart.class);

        return cartMono.flatMap(cart ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(restApiServices.updateCart(id,cart), Cart.class));
    }


    public Mono<ServerResponse> deleteCart(ServerRequest serverRequest) {
        final long id= Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.noContent().build(restApiServices.deleteCart(id))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
