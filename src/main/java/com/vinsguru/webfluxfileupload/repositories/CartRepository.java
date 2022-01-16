package com.vinsguru.webfluxfileupload.repositories;

import com.vinsguru.webfluxfileupload.Models.Cart;
import com.vinsguru.webfluxfileupload.Models.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

//create cart repository R2db (reactive database)
@Repository
public interface CartRepository extends ReactiveCrudRepository<Cart, Long> {
    @Query("select * from cart where user_id =:userId")
    Flux<Cart> findCartById(Long userId);
}
