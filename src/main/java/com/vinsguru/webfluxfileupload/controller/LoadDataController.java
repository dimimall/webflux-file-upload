package com.vinsguru.webfluxfileupload.controller;

import com.vinsguru.webfluxfileupload.repositories.ProductRepository;
import com.vinsguru.webfluxfileupload.services.RestApiServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class LoadDataController {

    private final Path basePath = Paths.get("./src/main/resources/upload/");
    private final ProductRepository productRepository;
    private final RestApiServices restApiServices;

    public LoadDataController(ProductRepository productRepository, RestApiServices restApiServices){
        this.productRepository = productRepository;
        this.restApiServices = restApiServices;
    }

//    @RequestMapping(value = "create", method = RequestMethod.POST)
//    public Mono<Product> addNewProduct(@RequestBody Product product) {
//        if (product != null){
//            product.toString();
//        }
//        return csvServices.newProduct(product);
//    }

    @RequestMapping(value = "/add/redirect", method = RequestMethod.GET)
    public Mono<Void> redirectHome(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("/product"));
        return response.setComplete();
    }

}
