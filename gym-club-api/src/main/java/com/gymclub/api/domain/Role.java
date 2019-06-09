package com.gymclub.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Xiaoming.
 * Created on 2019/03/12 23:18.
 * Description :
 */
@Data
@Document
public class Role implements Serializable {
    private static final long serialVersionUID = 4948892981871034574L;
    @Id
    private int id;

    private RoleName name;

    @JsonIgnoreProperties(ignoreUnknown = true, value = {"roles"})
    private List<UmUser> users = new LinkedList<>();

    public Role(RoleName roleName) {
        this.name = roleName;
    }

    public enum RoleName {
        ROLE_USER, ROLE_ADMIN
    }

    public Role() {
    }

    @Override
    public String toString() {
        return this.name.toString();
    }


}
