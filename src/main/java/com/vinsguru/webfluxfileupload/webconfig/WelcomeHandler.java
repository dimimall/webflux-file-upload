package com.vinsguru.webfluxfileupload.webconfig;

import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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

    public Mono<ServerResponse> addNewProduct(ServerRequest serverRequest) {
        Mono<Product> productMono = serverRequest.bodyToMono(Product.class);
        return productMono.flatMap(product ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(csvServices.newProduct(product), Product.class));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
        //final long id = Long.parseLong(serverRequest.pathVariable("id"));
        Mono<Product> productMono = serverRequest.bodyToMono(Product.class);
        return productMono.flatMap(product ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(csvServices.updateProduct(product), Product.class));
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {
        final long id= Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.noContent().build(csvServices.deleteProduct(id))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
