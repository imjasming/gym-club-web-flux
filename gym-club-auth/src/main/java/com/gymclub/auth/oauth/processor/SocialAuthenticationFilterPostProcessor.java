package com.gymclub.auth.oauth.processor;

import org.springframework.social.security.SocialAuthenticationFilter;

public interface SocialAuthenticationFilterPostProcessor {
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
