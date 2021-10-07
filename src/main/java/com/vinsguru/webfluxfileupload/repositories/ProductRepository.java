package com.vinsguru.webfluxfileupload.repositories;

import com.vinsguru.webfluxfileupload.Models.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByItemName(String itemName);
}
