package com.gymclub.api.controller;

import com.gymclub.api.domain.Trainer;
import com.gymclub.api.repository.TrainerRepository;
import com.gymclub.api.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.gymclub.api.util.RequestUtil.parseIntParam;

/**
 * @author Xiaoming.
 * Created on 2019/04/29 20:20.
 */
@Api(tags = "TrainerController")
@Component
public class TrainerHandler {
    @Autowired
    private DataService dataService;
    @Autowired
    private TrainerRepository trainerRepository;

    @ApiOperation("show trainer's list in home page")
    public Mono<ServerResponse> getTrainerList(ServerRequest request) {
        final int pageNo = parseIntParam(request, "pageNo");
        final int pageSize = parseIntParam(request, "pageSize");
        return ServerResponse.ok().body(trainerRepository.findAll().skip(pageNo * pageSize).take(pageSize), Trainer.class);
    }

    @ApiOperation("user add a trainer")
    public Mono<ServerResponse> addUserSTrainer(ServerRequest request) {
        final String username = request.pathVariable("username");
        final int trainerId = parseIntParam(request, "trainerId");
        dataService.addUserTrainerByID(username, trainerId);
        final Flux<Trainer> trainerList = dataService.getUserTrainers(username);
        return ServerResponse.ok().body(trainerList, Trainer.class);
    }

    @ApiOperation("get user's trainer list")
    public Mono<ServerResponse> getUserTrainerList(ServerRequest request) {
        final String username = request.pathVariable("username");
        final Flux<Trainer> trainerList = dataService.getUserTrainers(username);

        return ServerResponse.ok().body(trainerList, Trainer.class);
    }
}
