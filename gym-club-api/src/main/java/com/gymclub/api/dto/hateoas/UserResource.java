package com.gymclub.api.dto.hateoas;

import com.gymclub.api.domain.primary.UmUser;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    private final UmUser umUser;


    public UserResource(UmUser umUser) {
        this.umUser = umUser;
    }

    public UmUser getTrainer() {
        return umUser;
    }
}

