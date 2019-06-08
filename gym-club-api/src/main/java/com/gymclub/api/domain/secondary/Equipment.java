package com.gymclub.api.domain.secondary;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Equipment implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String eName;
    private double price;

    public Equipment() {
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", eName='" + eName + '\'' +
                ", price=" + price +
                '}';
    }
}
