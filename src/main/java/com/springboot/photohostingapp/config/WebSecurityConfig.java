package com.springboot.photohostingapp.config;

import com.springboot.photohostingapp.model.AppUser;
import com.springboot.photohostingapp.repo.AppUserRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private AppUserRepo appUserRepo;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public WebSecurityConfig(UserDetailsService userDetailsService, AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable(); // for Vaadin
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createAppUser(){
        AppUser appUserUser = new AppUser("UserJan", passwordEncoder().encode("UserJan"), "ROLE_USER");
        AppUser appUserAdmin = new AppUser("AdminJan", passwordEncoder().encode("AdminJan"), "ROLE_ADMIN");

        appUserRepo.save(appUserUser);
        appUserRepo.save(appUserAdmin);
    }
}
