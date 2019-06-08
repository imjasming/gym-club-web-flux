package com.gymclub.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Xiaoming.
 * Created on 2019/03/29 15:10.
 * 终于知道为什么中国程序员不喜欢用 Hibernate 了 —— hibernate 把彼此孤立的模块连成了一体，或冗余，或耦合；
 * 结论：mybatis
 */

@Entity
@Data
public class UmUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @JsonIgnore
    @Column(length = 128)
    private String password;

    @Column(unique = true)
    private String email;

    private String nickname;

    @Column
    private Date lastPasswordReset;

    @JsonIgnore
    private boolean enable;

    private String intro;

    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "rid", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"users"})
    private List<Role> roles = new LinkedList<>();

    @JoinTable(name = "user_trainer_relation", joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tid", referencedColumnName = "id")})
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // 巨TMD坑
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"users"})
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

    public void setRoles(List<Role> roles) {
        roles.forEach(item -> item.getUsers().add(this));
        this.roles.addAll(roles);
    }
}
