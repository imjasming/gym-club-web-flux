package com.gymclub.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Xiaoming.
 * Created on 2019/03/29 15:10.
 */

@Data
@Document
public class UmUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String nickname;

    private Date lastPasswordReset;

    @JsonIgnore
    private boolean enable;

    private String intro;

    @JsonIgnoreProperties(ignoreUnknown = true, value = {"users"})
    @DBRef
    private List<Role> roles = new LinkedList<>();

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // 巨TMD坑
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"users"})
    @DBRef
    private List<Trainer> trainers = new LinkedList<>();

    public UmUser() {
    }

    public UmUser(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public UmUser(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /*public void setRoles(List<Role> roles) {
        roles.forEach(item -> item.getUsers().add(this));
        this.roles.addAll(roles);
    }*/
}
