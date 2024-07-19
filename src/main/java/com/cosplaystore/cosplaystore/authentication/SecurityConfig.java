package com.cosplaystore.cosplaystore.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private String[] PUBLIC = {
            "/catetory/add", "/catetory/update", "/catetory/delete", "/product/all", "/catetory/{id}"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.POST, "/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                .anyRequest().authenticated());
        http.csrf(AbstractHttpConfigurer::disable);
        // http.oauth2ResourceServer(oath2 -> oath2.jwt(
        // jwtconfig -> jwtconfig.decoder(jwtDecoder())
        // .jwtAuthenticationConverter(jwtAuthenticationConverter()))
        // .authenticationEntryPoint(new AuthEntryPoint()));
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // config.setAllowCredentials(true); // you USUALLY want this
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    @Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDate enrollmentDate;

    // constructors, getters and setters
}
}
