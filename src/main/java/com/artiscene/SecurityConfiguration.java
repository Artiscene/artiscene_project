package com.artiscene;

import com.artiscene.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Brian on 2/21/17.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsLoader userDetails;

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .formLogin()
                    .loginPage("/")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/logout")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .authorizeRequests()
                    .antMatchers("/projects/create")
                    .authenticated();
//                .and()
//                .antMatchers("/user/updatePassword*",
//                        "/user/savePassword*",
//                        "/updatePassword*")
//                .hasAuthority("CHANGE_PASSWORD_PRIVILEGE");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }
}
