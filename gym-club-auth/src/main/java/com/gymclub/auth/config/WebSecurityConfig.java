package com.gymclub.auth.config;

import com.gymclub.auth.component.RestAuthenticationEntryPoint;
import com.gymclub.auth.handler.FormLoginAuthenticationSuccessHandler;
import com.gymclub.auth.jwt.JwtAuthenticationTokenFilter;
import com.gymclub.auth.service.AuthUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Xiaoming.
 * Created on 2019/02/07 23:48.
 * Description : 以下代码全是坑 艹
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthUserDetailsService authUserDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private FormLoginAuthenticationSuccessHandler formLoginAuthenticationSuccessHandler;
//    @Autowired
//    OAuth2ClientContext oauth2ClientContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //使用JWT，无需csrf
                .csrf().disable()
                //don't create session
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/img/**",
                        "/static/**",
                        "/resources/static/img/**",
                        "/*.jpg",
                        "/*.png"
                ).permitAll()
                .antMatchers("/swagger-resources/**", "/v2/api-docs/**").permitAll()  // swagger资源
                .antMatchers("/signup/**", "/social/**", "/auth/**", "/connect/**", "/register", "/oauth2/**", "/greeting").permitAll()// 对登录注册要允许匿名访问
                .antMatchers(HttpMethod.OPTIONS).permitAll()//跨域请求会先进行一次options请求
                .anyRequest().authenticated()  // 除上面外的所有请求全部需要鉴权认证
                .and()
                .logout().permitAll()
                .and().cors()  //csrf被禁用后,如果使用跨域,就导致axios不能正常获取error.response -- 巨坑
        ;

        log.info("=========== security 授权路径配置完毕 ============");

        http.oauth2Login()
                .successHandler(formLoginAuthenticationSuccessHandler)
        ;

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        http.headers().cacheControl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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

        log.info("=========== cors created ===========");

        return new CorsFilter(source);
    }
}
