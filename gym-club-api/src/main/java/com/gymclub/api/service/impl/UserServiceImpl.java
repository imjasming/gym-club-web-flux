package com.gymclub.api.service.impl;

import com.gymclub.api.domain.UmUser;
import com.gymclub.api.domain.UserInfo;
import com.gymclub.api.dto.PwdChangeDTO;
import com.gymclub.api.dto.UserProfile;
import com.gymclub.api.repository.UserRepository;
import com.gymclub.api.repository.UserInfoRepository;
import com.gymclub.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author Xiaoming.
 * Created on 2019/03/29 20:46.
 */
@Slf4j
@Service("userService")
//@CacheConfig(cacheNames = "com.xm.service.userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    /*@Autowired
    private AuthUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;*/


    /*@Override
    public String login(String username, String password) {
        String token = null;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            final Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            token = jwtTokenUtil.generateToken(userDetails);

            //TODO: maybe we can update user's lastlogin or log
            log.info("user[{}] login", username);
        } catch (AuthenticationException e) {
            log.warn("user[{}] login failed: {}", username, e.getMessage());
            e.printStackTrace();
        }

        return token;
    }*/

    @Override
    //@Cacheable(value = "userInfo", key = "#username")
    public UserInfo getUserInfoByName(String username) {
        UmUser user = userRepository.findByUsername(username).block();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(Objects.requireNonNull(user), userInfo);
        return userInfo;
    }

    @Transactional
    @Override
    //@CachePut(key = "#username")
    public Mono<UserInfo> updateProfile(UserProfile newProfile, String username) {
        final String email = newProfile.getEmail();
        if (userRepository.existsByEmail(email)) {
            return null;
        }
        //userRepository.updateUmUserEmail(username, email);

        return userRepository.findByUsername(username).map(user -> {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(Objects.requireNonNull(user), userInfo);
            return userInfo;
        });
    }

    @Transactional(value = "transactionManagerPrimary")
    @Override
    public Mono<UmUser> changePassword(PwdChangeDTO data) {
        final String username = data.getUsername();
        Mono<UmUser> user = userRepository.findUserByUsernameAndPassword(username, data.getOldPassword());
        if (user.hasElement().block()) {
            return null;
        }
        //userRepository.updateUmUserPassword(username, data.getPassword());
        return userRepository.findByUsername(username);
    }
}
