package com.gymclub.api.dto.hateoas.hatoasResourceAssembler;

import com.gymclub.api.controller.HateoasController;
import com.gymclub.api.domain.primary.Trainer;
import com.gymclub.api.dto.hateoas.TrainResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class TrainerResourceAssembler extends ResourceAssemblerSupport<Trainer, TrainResource> {

    public TrainerResourceAssembler()
    {
        super(HateoasController.class, TrainResource.class);
    }

    @Override
    public TrainResource toResource(Trainer trainer) {
        TrainResource resource = createResourceWithId(trainer.getId(), trainer);
        return resource;
    }

    @Override
    protected TrainResource instantiateResource(Trainer entity) {
        return new TrainResource(entity);
    }
}
