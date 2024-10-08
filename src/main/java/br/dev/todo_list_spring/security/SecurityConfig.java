package br.dev.todo_list_spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


import br.dev.todo_list_spring.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/").permitAll()  // endpoints públicos
                .requestMatchers("/users/**", "/h2-console/**").hasRole("ADMIN") // endpoints apenas para ADMIN
                .requestMatchers("/tasks/**").hasAnyRole("USER", "ADMIN") // endpoints apenas para USER
                .anyRequest().authenticated()  // o restante requer autenticação
            )
            .formLogin(form -> form.defaultSuccessUrl("/swagger-ui/index.html", true))  // redireciona para o swagger após o login
            .logout((logout) -> logout.permitAll());  // logout padrão

        http.headers().frameOptions().disable();

        return http.build();
    }
}


