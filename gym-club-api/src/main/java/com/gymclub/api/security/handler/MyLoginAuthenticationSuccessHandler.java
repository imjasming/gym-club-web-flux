package com.gymclub.api.security.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

/**
 * @author Xiaoming.
 * Created on 2019/05/10 16:23.
 */
@Slf4j
@Component("myLoginAuthenticationSuccessHandler")
public class MyLoginAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Value("${front-end.login-success-redirect-uri}")
    private String FRONT_END_REDIRECT_URI;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        List<String> header = webFilterExchange.getExchange().getRequest().getHeaders().get("Authorization");
        if (header == null || header.isEmpty() || !header.get(0).startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }

        // 从前端 Authentication 中 decode 出 clientId，clientSecret，原 header：{username: 'client', password: 'secret'}
        String[] tokens = new String[0];
        try {
            tokens = OAuthTokenUtil.extractAndDecodeHeader(header.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert tokens.length == 2;

        String clientId = tokens[0];
        String clientSecret = tokens[1];

        OAuth2AccessToken token = OAuthTokenUtil.extractOAuthAccessToken(clientId, clientSecret,
                authorizationServerTokenServices, clientDetailsService, authentication);

        webFilterExchange.getExchange().getResponse()
                .writeWith(Mono.just(new DefaultDataBufferFactory().wrap(JSON.toJSONBytes(token))));
        return null;
    }
}