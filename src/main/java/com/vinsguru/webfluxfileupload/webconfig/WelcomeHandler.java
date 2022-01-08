package com.vinsguru.webfluxfileupload.webconfig;

import com.vinsguru.webfluxfileupload.Models.Cart;
import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.Models.User;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@Slf4j
public class WelcomeHandler {

    @Autowired
    private final CsvServices csvServices;

    public WelcomeHandler(CsvServices csvServices) {
        this.csvServices = csvServices;
    }

    public Mono<ServerResponse> listProducts(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(csvServices.getAll(), Product.class);
    }

    public Mono<ServerResponse> getProduct(ServerRequest serverRequest) {
        Mono<Product> studentMono = csvServices.getProduct(
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
                        .body(csvServices.newProduct(product), Product.class));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
        final long id = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<Product> productMono = serverRequest.bodyToMono(Product.class);

        return productMono.flatMap(product ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(csvServices.updateProduct(id,product), Product.class));
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {
        final long id= Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.noContent().build(csvServices.deleteProduct(id))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        Mono<User> userMono = csvServices.getUser(
                Long.parseLong(serverRequest.pathVariable("id")));
        return userMono.flatMap(user -> ServerResponse.ok()
                .body(fromValue(user)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addNewUser(ServerRequest serverRequest) {
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return userMono.flatMap(user ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(csvServices.newUser(user), User.class));
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        final long id = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<User> userMono = serverRequest.bodyToMono(User.class);

        return userMono.flatMap(user ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(csvServices.updateUser(id,user), User.class));
    }

    public Mono<ServerResponse> listUsers(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(csvServices.getAllUser(), User.class);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest serverRequest) {
        final long id= Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.noContent().build(csvServices.deleteUser(id))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getCart(ServerRequest serverRequest) {
        final String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(csvServices.getCartById(Long.parseLong(id)),Cart.class);
    }

    public Mono<ServerResponse> addNewCart(ServerRequest serverRequest) {
        Mono<Cart> cartMono = serverRequest.bodyToMono(Cart.class);
        return cartMono.flatMap(cart ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(csvServices.newCart(cart), Cart.class));
    }

    public Mono<ServerResponse> updateCart(ServerRequest serverRequest) {
        final long id = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<Cart> cartMono = serverRequest.bodyToMono(Cart.class);

        return cartMono.flatMap(cart ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(csvServices.updateCart(id,cart), Cart.class));
    }


    public Mono<ServerResponse> deleteCart(ServerRequest serverRequest) {
        final long id= Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.noContent().build(csvServices.deleteCart(id))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
