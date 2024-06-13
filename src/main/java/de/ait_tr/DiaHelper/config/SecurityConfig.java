package de.ait_tr.DiaHelper.config;

import de.ait_tr.DiaHelper.security.sec_filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private TokenFilter filter;

    public SecurityConfig(TokenFilter filter) {
        this.filter = filter;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(x -> x
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                        .requestMatchers("/api/register/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/auth").permitAll()
                        .requestMatchers("/api/auth/update").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login", "/api/auth/access").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/logout").hasRole("USER")
                        // .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("USER")//
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/users/favorites/add").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("USER")
                        //.requestMatchers(HttpMethod.GET, "/api/users/favorites/list").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/products/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/{productTitle}").hasRole("ADMIN")
                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .build();
                .httpBasic(AbstractHttpConfigurer::disable)//отключаем базовую авторизацию
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class) //встроили фильтр в цепочку фильтров
                .build();
    }
}
