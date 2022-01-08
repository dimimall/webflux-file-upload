package com.vinsguru.webfluxfileupload.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.vinsguru.webfluxfileupload.Models.Cart;
import com.vinsguru.webfluxfileupload.Models.Product;
import com.vinsguru.webfluxfileupload.Models.User;
import com.vinsguru.webfluxfileupload.repositories.CartRepository;
import com.vinsguru.webfluxfileupload.repositories.ProductRepository;
import com.vinsguru.webfluxfileupload.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class CsvServices {

    private final Path basePath = Paths.get("./src/main/resources/upload/");

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CartRepository cartRepository;

    public CsvServices(ProductRepository productRepository,UserRepository userRepository, CartRepository cartRepository){
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public Mono<Product> newProduct(Product product){
        return productRepository.save(product);
    }

    public Flux<Product> getAll(){
        return productRepository.findAll();
    }

    public Mono<Product> getProduct(long id) { return productRepository.findById(id); }

    public Mono<User> getUser(long id) { return userRepository.findById(id); }

    public Mono<User> newUser(User user){
        return userRepository.save(user);
    }

    public Mono<Product> updateProduct(long id,Product productMono) {
        return this.productRepository.findById(id)
               .flatMap(product -> {
                   product.setItemName(productMono.getItemName());
                   product.setProductPrice(productMono.getProductPrice());
                   return productRepository.save(product);
               });
    }

    public Mono<User> updateUser(long id,User user) {
        return this.userRepository.findById(id)
                .flatMap(user1 -> {
                    user1.setName(user.getName());
                    user1.setRole(user.getRole());
                    return userRepository.save(user1);
                });
    }

    public Mono<Void> deleteProduct(final long id){
        return this.productRepository.findById(id)
                .flatMap(product ->
                        this.productRepository.delete(product));
    }

    public Flux<User> getAllUser(){
        return userRepository.findAll();
    }

    public Mono<Void> deleteUser(final long id){
        return this.userRepository.findById(id)
                .flatMap(user ->
                        this.userRepository.delete(user));
    }

    public Mono<Cart> newCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Flux<Cart> getCartById(Long id){
        return cartRepository.findCartById(id);
    }

    public Mono<Cart> updateCart(long id,Cart cart) {
        return this.cartRepository.findById(id)
                .flatMap(cart1 -> {
                    cart1.setProductId(cart.getProductId());
                    cart1.setProductName(cart.getProductName());
                    cart1.setProductPrice(cart.getProductPrice());
                    cart1.setUserId(cart.getUserId());
                    return cartRepository.save(cart1);
                });
    }

    public Mono<Void> deleteCart(final long id){
        return this.cartRepository.findById(id)
                .flatMap(cart ->
                        this.cartRepository.delete(cart));
    }
}
