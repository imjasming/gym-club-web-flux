package com.gymclub.api.security.authorize;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Xiaoming.
 * Created on 2019/06/13 00:20.
 */
public class MyGrantedAuthoritiesExcutor extends JwtAuthenticationConverter {
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Collection<String> authorities = (Collection<String>)
                jwt.getClaims().get("roles");

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
