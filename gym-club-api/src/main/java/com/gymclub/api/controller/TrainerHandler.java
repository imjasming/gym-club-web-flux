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

import static com.gymclub.api.controller.RequestUtil.parseIntParam;

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
    //@GetMapping("/trainers")
    public Mono<ServerResponse> getTrainerList(
           /* @RequestParam("pageSize") int pageSize,
            @RequestParam("pageNo") int pageNo*/
            ServerRequest request
    ) {
        final int pageNo = parseIntParam(request, "pageNo");
        final int pageSize = parseIntParam(request, "pageSize");
        return ServerResponse.ok().body(trainerRepository.findAll().skip(pageNo * pageSize).take(pageSize), Trainer.class);
    }

    @ApiOperation("user add a trainer")
    //@PostMapping("/user/{username}/trainer")
    public Mono<ServerResponse> addUserSTrainer(
            //@PathVariable String username, @RequestParam("trainerId") int trainerId
            ServerRequest request
    ) {
        final String username = request.pathVariable("username");
        final int trainerId = parseIntParam(request, "trainerId");
        dataService.addUserTrainerByID(username, trainerId);
        final Flux<Trainer> trainerList = dataService.getUserTrainers(username);
        return ServerResponse.ok().body(trainerList, Trainer.class);
    }

    @ApiOperation("get user's trainer list")
    //@GetMapping("/user/{username}/trainers")
    public Mono<ServerResponse> getUserTrainerList(
            //@PathVariable("username") String username
            ServerRequest request
    ) {
        final String username = request.pathVariable("username");
        final Flux<Trainer> trainerList = dataService.getUserTrainers(username);

        return ServerResponse.ok().body(trainerList, Trainer.class);
    }
}
