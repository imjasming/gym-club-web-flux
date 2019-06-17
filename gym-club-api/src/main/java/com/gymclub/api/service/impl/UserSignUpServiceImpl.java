package com.gymclub.api.service.impl;

import com.gymclub.api.domain.Role;
import com.gymclub.api.domain.UmUser;
import com.gymclub.api.dto.CommonRestResult;
import com.gymclub.api.dto.UserSignUpRequest;
import com.gymclub.api.repository.MyRoleRepository;
import com.gymclub.api.repository.UserRepository;
import com.gymclub.api.service.UserSignUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Xiaoming.
 * Created on 2019/05/23 21:38.
 */
@Slf4j
@Service
public class UserSignUpServiceImpl implements UserSignUpService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MyRoleRepository roleRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private void createCommonUser(UmUser newUser, Role.RoleName rname) {
        newUser.setLastPasswordReset(new Date());
        newUser.setEnable(true);

        if (!roleRepository.existsRoleById(1)) {
            Role role1 = new Role(Role.RoleName.ROLE_USER);
            Role role2 = new Role(Role.RoleName.ROLE_ADMIN);
            roleRepository.save(role1);
            roleRepository.save(role2);
        }

        newUser.getRoles().add(roleRepository.findByName(rname).block());
        userRepository.save(newUser);
    }

    @Override
    public UmUser createUser(UserSignUpRequest signUpParam) {
        UmUser newUser = new UmUser();
        BeanUtils.copyProperties(signUpParam, newUser);

        final String password = signUpParam.getPassword();
        if (password != null) {
            final String rawPassword = passwordEncoder.encode(password);
            newUser.setPassword(rawPassword);
        }

        createCommonUser(newUser, Role.RoleName.ROLE_USER);
        return newUser;
    }

    @Override
    public CommonRestResult register(UserSignUpRequest param) {
        String message;
        if ((message = validateUsername(param.getUsername())) != null) {
            return CommonRestResult.failure(message);
        }
        if ((message = validateEmail(param.getEmail())) != null) {
            return CommonRestResult.failure(message);
        }
        return CommonRestResult.ok(createUser(param));
    }

    private String validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return "用户名不能为空。";
        }
        if (userRepository.existsByUsername(username)) {
            return "用户名已存在。";
        }
        return null;
    }

    private String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "邮箱不能为空。";
        }
        if (userRepository.existsByUsername(email)) {
            return "邮箱已存在。";
        }
        return null;
    }
}
