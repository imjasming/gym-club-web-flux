package com.gymclub.api.security;

import com.gymclub.api.security.authorize.MyGrantedAuthoritiesExcutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationCodeAuthenticationTokenConverter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import reactor.core.publisher.Mono;

/**
 * @author Xiaoming.
 * Created on 2019/06/10 23:17.
 */
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfig {
    @Autowired
    private ReactiveAuthenticationManager authenticationManager;

    @Autowired
    private ServerAuthenticationSuccessHandler serverAuthenticationSuccessHandler;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .cors();

        http
                .authorizeExchange()
                .pathMatchers("/auth/**", "/login", "/sign-up").permitAll()
                .anyExchange().authenticated();

        // ...
        http.formLogin()
                //.loginPage("/login")
                .authenticationFailureHandler((exchange, exception) -> Mono.error(exception))
                .authenticationSuccessHandler(serverAuthenticationSuccessHandler);


        http
                .oauth2Login()
                .authenticationConverter(new ServerOAuth2AuthorizationCodeAuthenticationTokenConverter(clientRegistrations()))
                .authenticationManager(authenticationManager)
                .clientRegistrationRepository(clientRegistrations());

        http
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(grantedAuthoritiesExtractor());
        return http.build();
    }

    Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        MyGrantedAuthoritiesExcutor extractor = new MyGrantedAuthoritiesExcutor();
        return new ReactiveJwtAuthenticationConverterAdapter(extractor);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");  // 1 设置访问源地址
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");  // 2 设置访问源请求头
        config.addAllowedMethod("*");  // 3 设置访问源请求方法
        //config.addExposedHeader("Content-Range");//这里是需要额外配置的header内容

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);  // 4 对接口配置跨域设置
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);

        return new CorsFilter(source);
    }

    @Bean
    ReactiveClientRegistrationRepository clientRegistrations() {
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("github")
                .clientId("de87e995aa6c1c726646")
                .clientSecret("5e0aadf8a2b9203e318fc2b4e938862d255efbea")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationUri("https://github.com/login/oauth/authorize")
                .scope("user")
                .tokenUri("https://github.com/login/oauth/access_token")
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .userInfoUri("https://api.github.com/user")
                .userNameAttributeName("login")
                .build();
        return new InMemoryReactiveClientRegistrationRepository(clientRegistration);
    }
}
