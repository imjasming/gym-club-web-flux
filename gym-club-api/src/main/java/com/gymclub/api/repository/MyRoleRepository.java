package com.gymclub.api.repository;


import com.gymclub.api.domain.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2018/11/22 11:30.
 * Description :This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface MyRoleRepository extends ReactiveMongoRepository<Role, Integer>{

    Mono<Role> findByName(Role.RoleName rname);
    boolean existsRoleById(Integer id);
}