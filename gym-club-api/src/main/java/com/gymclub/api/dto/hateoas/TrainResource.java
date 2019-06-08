package com.gymclub.api.dto.hateoas;

import com.gymclub.api.domain.primary.Trainer;
import org.springframework.hateoas.ResourceSupport;

public class TrainResource extends ResourceSupport {

    private final Trainer trainer;

    public TrainResource(Trainer trainer) {
        this.trainer = trainer;
    }

    public Trainer getTrainer() {
        return trainer;
    }
}

