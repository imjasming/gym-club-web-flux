package com.gymclub.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
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
