package com.exam.plan.config;

import com.exam.plan.service.impl.AccountDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AccountDetailsServiceImpl myUserDetailsService;

    @Bean
    @Override
    public AccountDetailsServiceImpl userDetailsService() {
        return new AccountDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        //  允许所有用户访问"/"和"/index.html"
//        http.authorizeRequests()
//                .antMatchers("/", "/index").permitAll()
//                .anyRequest().authenticated()   // 其他地址的访问均需验证权限
//                .and()
//                .cors()
//                .disable()
//                .csrf().disable() //暂时关闭csrf，不知道java获取csrf放入form表单
//                .formLogin() // form提交登录
//                .loginPage("/login")   //  登录页
//                .failureUrl("/login-error").permitAll()
//                .and()
//                .logout()  //   添加 /logout 访问点，能退出
//                .logoutSuccessUrl("/index");  //退出后访问

        http
                // 禁用页面缓存
                .headers()
                .cacheControl()
                .and()
                .and()
                // 关闭 cors 验证
                .cors()
                .disable()
                // 关闭 csrf 验证
                .csrf()
                .disable()
                // 无状态 session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 异常处理
                .exceptionHandling()
                // 因为 RESTFul 没有登录界面所以只能显示未登录
//                .authenticationEntryPoint(this.myAuthenticationEntryPoint)
                .and()
                // 身份过滤器
//                .addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                // 对所有的请求都做权限校验
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 允许匿名 GET 请求
                .antMatchers("/swagger-ui.html**", "/swagger-resources**", "/webjars/**", "/v2/**")
                .permitAll();
                // 允许匿名 POST 请求
//                .antMatchers(HttpMethod.POST, "/account", "/account/token")
//                .permitAll()
                // 允许匿名 DELETE 请求
//                .antMatchers(HttpMethod.DELETE, "/upload/image/**", "/upload/video/**")
//                .permitAll()
                // 更新token
//                .antMatchers(HttpMethod.PUT, "/wechat/token")
//                .permitAll()
                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest()
//                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(this.userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
