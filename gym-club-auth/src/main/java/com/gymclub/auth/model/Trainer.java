package com.gymclub.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
public class Trainer implements Serializable {
    @Id
    private Integer id;

    private String name;
    private int age;
    private String position;
    private String email;
    private String telephone;
    private double salary;
    private String intro;

    @JsonIgnoreProperties(ignoreUnknown = true, value = {"trainers"})
    private List<UmUser> users = new LinkedList<>();

    private Gym gym;

    public Trainer() {
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", intro='" + intro + '\'' +
                ", users=" + users +
                ", gym=" + gym +
                '}';
    }
}
