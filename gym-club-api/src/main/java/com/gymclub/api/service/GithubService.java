package com.gymclub.api.service;


import com.gymclub.api.domain.UmUser;
import com.gymclub.api.dto.GithubAuthServiceResult;

/**
 * @author Xiaoming.
 * Created on 2019/05/15 00:11.
 */
public interface GithubService {
    UmUser getUserByGithubId(String id);

    GithubAuthServiceResult getTokenByUsername(String username);
}
