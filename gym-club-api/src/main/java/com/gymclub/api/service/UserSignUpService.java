package com.gymclub.api.service;

import com.gymclub.api.domain.UmUser;
import com.gymclub.api.dto.CommonRestResult;
import com.gymclub.api.dto.UserSignUpRequest;

/**
 * @author Xiaoming.
 * Created on 2019/05/23 21:36.
 */
public interface UserSignUpService {

    UmUser createUser(UserSignUpRequest request);

    CommonRestResult register(UserSignUpRequest signUpParam);
}
