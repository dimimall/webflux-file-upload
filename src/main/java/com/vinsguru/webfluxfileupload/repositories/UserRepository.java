package com.vinsguru.webfluxfileupload.repositories;

import com.vinsguru.webfluxfileupload.Models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
