package com.faketri.market.security;

import com.faketri.market.service.user.UserDetailsServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsServerImpl userDetailsServiceImpl;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                   .cors(cors -> cors.configurationSource(request -> {
                       var corsConfiguration = new CorsConfiguration();
                       corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                       corsConfiguration.setAllowedMethods(List.of("GET",
                                                                   "POST",
                                                                   "PUT",
                                                                   "DELETE",
                                                                   "OPTIONS"
                       ));
                       corsConfiguration.setAllowedHeaders(List.of("*"));
                       corsConfiguration.setAllowCredentials(true);
                       return corsConfiguration;
                   }))
                   .authorizeHttpRequests((requests) -> requests.requestMatchers(
                           HttpMethod.GET,
                           "/api/**"
                   ).permitAll().requestMatchers(
                           HttpMethod.POST,
                           "/api/auth/**"
                   ).permitAll().anyRequest().authenticated())
                   .sessionManagement(manager -> manager.sessionCreationPolicy(
                           STATELESS))
                   .authenticationProvider(daoAuthenticationProvider())
                   .addFilterBefore(
                           jwtAuthenticationFilter,
                           UsernamePasswordAuthenticationFilter.class
                   )
                   .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(CustomUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService CustomUserDetailsService() {
        return userDetailsServiceImpl;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

}
