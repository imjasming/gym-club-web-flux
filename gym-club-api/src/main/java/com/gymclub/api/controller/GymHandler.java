package com.gymclub.api.controller;

import com.gymclub.api.domain.Gym;
import com.gymclub.api.repository.GymRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.gymclub.api.util.RequestUtil.parseIntParam;

/**
 * @author Xiaoming.
 * Created on 2019/04/22 22:31.
 */
@Api(tags = "GymController", value = "home gym list")
@Component
public class GymHandler {
    private final GymRepository gymRepository;

    public GymHandler(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @ApiOperation("in home page, list gyms info")
    public Mono<ServerResponse> getGymList(ServerRequest request) {
        final int pageNo = parseIntParam(request, "pageNo");
        final int pageSize = parseIntParam(request, "pageSize");
        return ServerResponse.ok().body(gymRepository.findAll().skip(pageNo * pageSize).take(pageSize), Gym.class);
    }

}
