package com.gymclub.api.dto.hateoas;

import com.gymclub.api.domain.secondary.Equipment;
import org.springframework.hateoas.ResourceSupport;

public class EquipmentResource extends ResourceSupport {

    private final Equipment equipment;


    public EquipmentResource(Equipment equipment) {
        this.equipment = equipment;
    }

    public Equipment getEquipment() {
        return equipment;
    }
}

