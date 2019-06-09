/*
package com.gymclub.api.router;

import com.gymclub.api.controller.TrainerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

*/
/**
 * @author Xiaoming.
 * Created on 2019/06/09 17:34.
 *//*

@Configuration
public class TrainerRouter {
    @Bean
    public RouterFunction<?> route(TrainerHandler handler) {
        return RouterFunctions.route()
                .path("/user", builder -> builder
                        .GET("/{username}/trainers", handler::getUserTrainerList)
                        .POST("/{username}/trainer", handler::addUserSTrainer))
                .GET("/trainers", handler::getTrainerList)
                .build();
    }
}
*/
