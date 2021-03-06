package com.hxht.logprocess.login;

import com.alibaba.fastjson.JSON;
import com.hxht.logprocess.core.http.model.ResMessage;
import com.hxht.logprocess.core.util.MD5;
import com.hxht.logprocess.usermange.model.User;
import com.hxht.logprocess.usermange.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@Slf4j
public class WebSercurityConfiguration extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(WebSercurityConfiguration.class);
    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests().antMatchers("/admin").authenticated()
                .anyRequest().permitAll().and()
                .formLogin().loginProcessingUrl("/server/login/user")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler((req, res, e) -> {
                    res.setContentType("application/json;charset=utf-8");
                    ResMessage resMessage = ResMessage.genErrorMessage(ResMessage.CodeDict.LoginError, "???????????????????????????????????????", e.getMessage());
                    String s = JSON.toJSONString(resMessage);
                    res.getWriter().write(s);
                    res.getWriter().flush();
                    res.getWriter().close();
                })
                .successHandler((req, res, auth) -> {
                    res.setContentType("application/json;charset=utf-8");
                    //????????????????????????????????????
                    User user_tmp = (User) auth.getPrincipal();
                    //???????????????????????????????????????
                    User user = userService.getUserById(user_tmp.getId());
                    ResMessage resMessage = ResMessage.genSucessMessage("????????????", user);
                    String s = JSON.toJSONString(resMessage);
                    res.getWriter().write(s);
                    res.getWriter().flush();
                    res.getWriter().close();
                }).and()
                .headers().xssProtection();
//                .httpBasic();
    }

    /**
     * ??????JSP???????????????
     * ????????????????????????????????????Spring?????????BeanNameViewResolver???????????????ID???????????????????????????????????????View?????????beans
     *
     * @return
     */
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        /** ??????????????????????????? */
        //resolver.setPrefix("/templates/");
        /** ??????????????????????????? */
        resolver.setSuffix(".html");
        return resolver;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                //??????MD5??????
                MD5 md5 = new MD5();
                String str = md5.getMD5ofStr((String) charSequence);
                return str;
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String ps = encode(rawPassword);
                return encodedPassword != null && encodedPassword.equals(encode(rawPassword));
            }
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }


}
