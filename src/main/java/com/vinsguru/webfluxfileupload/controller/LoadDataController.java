package com.vinsguru.webfluxfileupload.controller;

import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.repositories.ProductRepository;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.beans.Encoder;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class LoadDataController {

    private final Path basePath = Paths.get("./src/main/resources/upload/");
    private final ProductRepository productRepository;
    private final CsvServices csvServices;

    public LoadDataController(ProductRepository productRepository,CsvServices csvServices){
        this.productRepository = productRepository;
        this.csvServices = csvServices;
    }

//    @RequestMapping(value = "create", method = RequestMethod.POST)
//    public Mono<Product> addNewProduct(@RequestBody Product product) {
//        if (product != null){
//            product.toString();
//        }
//        return csvServices.newProduct(product);
//    }

    @RequestMapping(value = "/second/redirect", method = RequestMethod.GET)
    public Mono<Void> redirectHome(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.PERMANENT_REDIRECT);
        response.getHeaders().setLocation(URI.create("/second"));
        return response.setComplete();
    }

}
