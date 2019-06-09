package com.gymclub.auth.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Xiaoming.
 * Created on 2018/11/22 11:30.
 * Description :This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<UmUser, Integer> {

    Mono<UmUser> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UmUser findByEmail(String email);

    List<UmUser> findAllByEmail(String email);

    UmUser findUserByUsernameAndPassword(String username, String password);

    UmUser findUserByIdAndAndPassword(Long id, String password);
}
