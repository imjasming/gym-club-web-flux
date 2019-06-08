package com.gymclub.auth.service;

import com.gymclub.auth.dto.CommonRestResult;
import com.gymclub.auth.dto.UserSignUpRequest;
import com.gymclub.auth.dto.UserSocialBindParam;
import com.gymclub.auth.model.UmUser;

/**
 * @author Xiaoming.
 * Created on 2019/05/23 21:36.
 */
public interface UserService {

    UmUser createUser(UserSignUpRequest request);

    CommonRestResult register(UserSignUpRequest signUpParam);

    CommonRestResult bindOAuthUser(UserSocialBindParam param);
}
