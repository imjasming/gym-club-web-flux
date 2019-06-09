package com.gymclub.api.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Equipment implements Serializable {
    @Id
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
