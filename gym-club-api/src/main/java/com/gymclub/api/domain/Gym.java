package com.gymclub.api.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Gym implements Serializable {

    @Id
    private Integer id;
    private String gymName;
    private String location;
    private String intro;

    public Gym() {
    }

    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", gymName='" + gymName + '\'' +
                ", location='" + location + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
