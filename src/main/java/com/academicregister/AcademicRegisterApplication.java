package com.academicregister;

import com.academicregister.application.security.ISecurityService;
import com.academicregister.infraestucture.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@ComponentScan("com.academicregister")
public class AcademicRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcademicRegisterApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private ISecurityService securityService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(securityService), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated();
        }
    }
}
