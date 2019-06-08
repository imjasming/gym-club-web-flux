package com.gymclub.api.dto.hateoas;

import com.gymclub.api.domain.primary.Gym;
import org.springframework.hateoas.ResourceSupport;

public class GymResource extends ResourceSupport {

    private final Gym gym;


    public GymResource(Gym gym) {
        this.gym = gym;
    }

    public Gym getGym() {
        return gym;
    }
}

