package com.gymclub.api.repository;

import com.gymclub.api.domain.UmUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2018/11/22 11:30.
 * Description :This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<UmUser, Integer>, ReactiveCrudRepository<UmUser, Integer> {

    boolean existsByUsername(String username);
    Mono<UmUser> findByUsername(String username);

    Flux<UmUser> findAllByUsername(String username);

    boolean existsByEmail(String email);

    Mono<UmUser> findByEmail(String email);

    Flux<UmUser> findAllByEmail(String email);

    Mono<UmUser> findUserByUsernameAndPassword(String username, String password);

    Mono<UmUser> findUserByIdAndAndPassword(Long id, String password);
}