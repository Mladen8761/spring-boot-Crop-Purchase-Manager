package com.Mladen.otkupproizvoda.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(configurer->
                configurer.requestMatchers(HttpMethod.GET,"/operant").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/operant/zaduzivanjeForm").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/operant/razduzivanjeForm").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/operant/form").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/operant/save").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/operant/updateZaduzivanje").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/operant/updateRazduzivanje").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/transakcija/form").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/transakcija/save").hasRole("ADMIN")
                        .anyRequest().authenticated()

                );

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
