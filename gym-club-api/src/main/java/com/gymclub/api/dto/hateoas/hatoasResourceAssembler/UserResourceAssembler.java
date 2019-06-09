package com.gymclub.api.dto.hateoas.hatoasResourceAssembler;


import com.gymclub.api.controller.HateoasHandler;
import com.gymclub.api.domain.UmUser;
import com.gymclub.api.dto.hateoas.UserResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserResourceAssembler extends ResourceAssemblerSupport<UmUser, UserResource> {

    public UserResourceAssembler()
    {
        super(HateoasHandler.class, UserResource.class);
    }

    @Override
    public UserResource toResource(UmUser umUser) {
        UserResource resource = createResourceWithId(umUser.getId(), umUser);
        return resource;
    }

    @Override
    protected UserResource instantiateResource(UmUser entity) {
        return new UserResource(entity);
    }
}
