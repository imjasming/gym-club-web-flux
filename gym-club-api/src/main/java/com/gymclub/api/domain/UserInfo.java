package com.gymclub.api.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Xiaoming.
 * Created on 2019/04/18 17:44.
 */
@Data
@Document
public class UserInfo implements Serializable {
    private Integer id;

    @Id
    private String username;

    private String email;

    private String nickname;

    private Date lastPasswordReset;

    private String intro;

    public UserInfo() {
    }
}
