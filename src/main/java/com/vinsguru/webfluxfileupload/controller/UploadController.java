package com.vinsguru.webfluxfileupload.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.vinsguru.webfluxfileupload.Models.Cart;
import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.Models.User;
import com.vinsguru.webfluxfileupload.services.CsvServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins ={"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class UploadController {

    private final Path basePath = Paths.get("./src/main/resources/upload/");
    @Autowired
    private final CsvServices csvServices;

    public UploadController(CsvServices csvServices){
        this.csvServices = csvServices;
    }

    @GetMapping("/products")
    public Flux<Product> listProducts(){
        return csvServices.getAll();
    }

    @GetMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> product(@PathVariable Long id) {
        return csvServices.getProduct(id).
        map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping(value = "/create")
    public Mono<Product> addNewProduct(@RequestBody Product product) {
        if (product != null){
            product.toString();
            System.out.println(product.toString());
        }
        return csvServices.newProduct(product);
    }

    @PutMapping("/upload/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable Long id,@RequestBody Product productMono){
        return this.csvServices.updateProduct(id,productMono).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable Long id){
        return this.csvServices.deleteProduct(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<User>> user(@PathVariable Long id) {
        return csvServices.getUser(id).
                map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping(value = "/create/user")
    public Mono<User> addNewUser(@RequestBody User user) {
        if (user != null){
            user.toString();
            System.out.println(user.toString());
        }
        return csvServices.newUser(user);
    }

    @PutMapping("/upload/user/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable Long id,@RequestBody User user){
        return this.csvServices.updateUser(id,user).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/users")
    public Flux<User> listUsers(){
        return csvServices.getAllUser();
    }

    @DeleteMapping("/delete/user/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable Long id){
        return this.csvServices.deleteUser(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cart/{id}")
    public Flux<Cart> cart(@PathVariable Long id) {
        return csvServices.getCartById(id);

    }

    @PostMapping(value = "/create/cart")
    public Mono<Cart> addNewCart(@RequestBody Cart cart) {
        if (cart != null){
            cart.toString();
            System.out.println(cart.toString());
        }
        return csvServices.newCart(cart);
    }

    @PutMapping("/upload/cart/{id}")
    public Mono<ResponseEntity<Cart>> updateCart(@PathVariable Long id,@RequestBody Cart cart){
        return this.csvServices.updateCart(id,cart).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/cart/{id}")
    public Mono<ResponseEntity<Void>> deleteCart(@PathVariable Long id){
        return this.csvServices.deleteCart(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}