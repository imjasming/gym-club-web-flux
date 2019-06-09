package com.gymclub.api.dto.hateoas.hatoasResourceAssembler;


import com.gymclub.api.controller.HateoasHandler;
import com.gymclub.api.domain.Equipment;
import com.gymclub.api.dto.hateoas.EquipmentResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class EquipmentResourceAssembler extends ResourceAssemblerSupport<Equipment, EquipmentResource> {

    public EquipmentResourceAssembler() {
        super(HateoasHandler.class, EquipmentResource.class);
    }

    @Override
    public EquipmentResource toResource(Equipment equipment) {
        EquipmentResource resource = createResourceWithId(equipment.getId(), equipment);
        return resource;
    }

    @Override
    protected EquipmentResource instantiateResource(Equipment entity) {
        return new EquipmentResource(entity);
    }
}
