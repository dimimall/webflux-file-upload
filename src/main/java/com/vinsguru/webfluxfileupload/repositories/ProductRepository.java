package com.vinsguru.webfluxfileupload.repositories;

import com.vinsguru.webfluxfileupload.Models.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

//create product repository R2db (reactive database)
@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    @Query("select * from products where itemName =:itemName")
    Mono<Product> findByItemName(String itemName);

}
