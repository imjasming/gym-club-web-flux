package com.gymclub.auth.dao;


import com.gymclub.auth.model.UmUser;

/**
 * @author Xiaoming.
 * Created on 2019/05/27 17:19.
 */
public interface UserDao {
    int insert(UmUser user);

    int getNextId();

    void updateNextId();
}
