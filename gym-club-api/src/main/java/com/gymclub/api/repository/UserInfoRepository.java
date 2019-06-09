package com.gymclub.api.repository;


import com.gymclub.api.domain.UserInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2019/04/27 21:25.
 */
@Repository
public interface UserInfoRepository extends ReactiveMongoRepository<UserInfo, String> {
    Mono<UserInfo> findByUsername(String username);
}
