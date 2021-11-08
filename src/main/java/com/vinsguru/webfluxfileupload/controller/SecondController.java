package com.vinsguru.webfluxfileupload.controller;

import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.repositories.ProductRepository;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

//@RestController
//@RequestMapping("/second")
public class SecondController {

//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    CsvServices csvServices;

//    @GetMapping("/products")
//    public String second(final Model model){
//        Flux<Product> productFlux = productRepository.findAll();
//        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
//                new ReactiveDataDriverContextVariable(productFlux, 1);
//
//        model.addAttribute("products", reactiveDataDrivenMode);
//        return "second";
//    }
}
