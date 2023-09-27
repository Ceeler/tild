package ru.example.tild.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.example.tild.security.JwtFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/auth/**","/user/signup",
                                        "/swagger", "/swagger-ui/**", "/swagger-ui/index.html", "/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated())
                        .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
                //.addFilterAfter(jwtFilter,UsernamePasswordAuthenticationFilter.class).build();

    }
//        return http
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests(
//                        authz -> authz
//                                .antMatchers("/api/auth/login", "/api/auth/token").permitAll()
//                                .anyRequest().authenticated()
//                                .and()
//                                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                ).build();
//        return http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/", "/orders").permitAll()
//                .antMatchers(HttpMethod.POST, "/order/**").hasAnyRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/order/**").hasAnyRole("ADMIN")
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//
//    }


    }