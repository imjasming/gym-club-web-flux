package com.gymclub.api.security;

import com.gymclub.api.repository.UserRepository;
import com.gymclub.api.security.bo.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2019/06/10 23:21.
 */
@Service
public class MyReactiveUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new UsernameNotFoundException("User Not Found"))))
                .map(MyUserDetails::new);
    }
}