package com.vinsguru.webfluxfileupload.webconfig;

import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WelcomeHandler {
    private CsvServices csvServices;

    public WelcomeHandler(CsvServices csvServices) {
        this.csvServices = csvServices;
    }

    public Mono<ServerResponse> addNewProduct(ServerRequest serverRequest) {
        Mono<Product> studentMono = serverRequest.bodyToMono(Product.class);
        return studentMono.flatMap(product ->
                ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(csvServices.newProduct(product), Product.class));
    }

}
