package com.vinsguru.webfluxfileupload.repositories;

import com.vinsguru.webfluxfileupload.Models.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

//create user repository R2db (reactive database)
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Query("select * from users where id =:id and password =:password")
    Mono<User> getUserByIdPassword(Long id, String password);
}
