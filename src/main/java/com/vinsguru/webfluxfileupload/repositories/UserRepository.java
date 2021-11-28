package com.vinsguru.webfluxfileupload.repositories;

import com.vinsguru.webfluxfileupload.Models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
