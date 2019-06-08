package com.gymclub.api.dto.hateoas.hatoasResourceAssembler;

import com.gymclub.api.controller.HateoasController;
import com.gymclub.api.domain.primary.Gym;
import com.gymclub.api.dto.hateoas.GymResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class GymResourceAssembler extends ResourceAssemblerSupport<Gym, GymResource>{

    public GymResourceAssembler()
    {
        super(HateoasController.class, GymResource.class);
    }

    @Override
    public GymResource toResource(Gym gym) {
        GymResource resource = createResourceWithId(gym.getId(), gym);
        return resource;
    }

    @Override
    protected GymResource instantiateResource(Gym entity) {
        return new GymResource(entity);
    }
}
