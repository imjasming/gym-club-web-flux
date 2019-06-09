package com.gymclub.auth.model;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Xiaoming.
 * Created on 2019/05/18 19:27.
 * Description :
 */
@Repository
public interface OAuthClientRepository extends ReactiveMongoRepository<OauthClient, String> {
    OauthClient findByClientId(String clientId);
}
