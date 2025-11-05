package com.example.demo.config;

import com.example.demo.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/systemuser/register","/systemuser/login").permitAll()
                        .requestMatchers("/systemuser/add").hasRole("ADMIN")
                        // ================= LIBRARIAN =================
                        .requestMatchers(HttpMethod.GET, "/book/**").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.POST, "/book/**").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.PUT, "/book/**").hasRole("LIBRARIAN")
                        .requestMatchers(HttpMethod.DELETE, "/book/**").hasRole("LIBRARIAN")

                        .requestMatchers("/author/**").hasRole("LIBRARIAN")
                        .requestMatchers("/publisher/**").hasRole("LIBRARIAN")
                        .requestMatchers("/category/**").hasRole("LIBRARIAN")
                        .requestMatchers("/member/**").hasRole("LIBRARIAN")

                        // ================= STAFF =================
                        .requestMatchers(HttpMethod.GET, "/book/**").hasRole("STAFF")
                        .requestMatchers("/borrowtransaction/**").hasRole("STAFF")

                        // ================= MEMBER =================
                        .requestMatchers(HttpMethod.GET, "/book/**").hasRole("MEMBER")
                        .requestMatchers(HttpMethod.GET, "/borrowtransaction/**").hasRole("MEMBER")

                        // أي request تاني لازم يكون authenticated
                        .anyRequest().authenticated()
                )
//                .httpBasic(Customizer.withDefaults());
                .httpBasic(httpBasic -> httpBasic.disable());


        return http.build();


    }

    // AuthenticationManager الجديد في Spring Security 6
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

