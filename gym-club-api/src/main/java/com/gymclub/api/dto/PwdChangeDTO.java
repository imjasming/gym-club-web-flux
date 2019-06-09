package com.gymclub.api.dto;

import lombok.Data;

/**
 * @author Xiaoming.
 * Created on 2019/06/09 21:16.
 */
@Data
public class PwdChangeDTO {
    private String username;
    private String oldPassword;
    private String password;
}
