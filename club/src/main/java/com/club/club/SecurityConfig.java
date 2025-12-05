package com.club.club; // Ajusta el paquete si es necesario

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Permitir estilos
                .anyRequest().authenticated() // Todo lo demás requiere login
            )
            .formLogin((form) -> form
                .defaultSuccessUrl("/club/list", true) // Al entrar, ir a la lista
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());
        
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // CREAMOS UN USUARIO: Usuario="admin", Contraseña="123"
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("123")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}