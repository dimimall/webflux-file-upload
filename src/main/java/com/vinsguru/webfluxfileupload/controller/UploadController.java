package com.vinsguru.webfluxfileupload.controller;

import com.vinsguru.webfluxfileupload.Models.Cart;
import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.Models.User;
import com.vinsguru.webfluxfileupload.services.RestApiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;

//rest controller
@CrossOrigin(origins ={"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class UploadController {

    private final Path basePath = Paths.get("./src/main/resources/upload/");
    @Autowired
    private final RestApiServices restApiServices;

    //constractor
    public UploadController(RestApiServices restApiServices){
        this.restApiServices = restApiServices;
    }

    //get all products
    @GetMapping("/products")
    public Flux<Product> listProducts(){
        return restApiServices.getAll();
    }

    //get all products by id of product
    @GetMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> product(@PathVariable Long id) {
        return restApiServices.getProduct(id).
        map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    //create new product
    @PostMapping(value = "/create")
    public Mono<Product> addNewProduct(@RequestBody Product product) {
        if (product != null){
            product.toString();
            System.out.println(product.toString());
        }
        return restApiServices.newProduct(product);
    }

    //update product by id
    @PutMapping("/upload/{id}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable Long id,@RequestBody Product productMono){
        return this.restApiServices.updateProduct(id,productMono).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //delete product by id
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable Long id){
        return this.restApiServices.deleteProduct(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //get user by id
    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<User>> user(@PathVariable Long id) {
        return restApiServices.getUser(id).
                map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    //create user
    @PostMapping(value = "/create/user")
    public Mono<User> addNewUser(@RequestBody User user) {
        if (user != null){
            user.toString();
            System.out.println(user.toString());
        }
        return restApiServices.newUser(user);
    }

    //update user by id
    @PutMapping("/upload/user/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable Long id,@RequestBody User user){
        return this.restApiServices.updateUser(id,user).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //get all users
    @GetMapping("/users")
    public Flux<User> listUsers(){
        return restApiServices.getAllUser();
    }

    //get user by id and password
    @GetMapping("/users/{id}/{password}")
    public Mono<ResponseEntity<User>> userByPassword(@PathVariable Long id, @PathVariable String password){
        return restApiServices.getUserByIdPassword(id,password)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //delete user by id
    @DeleteMapping("/delete/user/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable Long id){
        return this.restApiServices.deleteUser(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //get cart bu user id
    @GetMapping("/cart/{id}")
    public Flux<Cart> cart(@PathVariable Long id) {
        return restApiServices.getCartById(id);

    }

    //create cart for each user
    @PostMapping(value = "/create/cart")
    public Mono<Cart> addNewCart(@RequestBody Cart cart) {
        if (cart != null){
            cart.toString();
            System.out.println(cart.toString());
        }
        return restApiServices.newCart(cart);
    }

    //update cart by user id
    @PutMapping("/upload/cart/{id}")
    public Mono<ResponseEntity<Cart>> updateCart(@PathVariable Long id,@RequestBody Cart cart){
        return this.restApiServices.updateCart(id,cart).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    //delete cart by card id
    @DeleteMapping("/delete/cart/{id}")
    public Mono<ResponseEntity<Void>> deleteCart(@PathVariable Long id){
        return this.restApiServices.deleteCart(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}