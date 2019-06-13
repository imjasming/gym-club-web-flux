package com.gymclub.api.security;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


/**
 * @author Xiaoming.
 * Created on 2019/05/18 19:27.
 * Description :
 */
@Repository
public interface OAuthClientRepository extends ReactiveMongoRepository<OauthClient, String> {
    Mono<OauthClient> findByClientId(String clientId);
}
