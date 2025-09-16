package com.emranhss.merchandise.Config;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthenticationFilter jwtAuthenticationFilter,
                                           UserService userService) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/auth/login",
                                "/api/auth/logout",
                                "/api/auth/active/**",
                                "/api/auth/all",
                                "/api/admin/reg",
                                "/api/cashier/reg",
                                "/images/**"
                        ).permitAll()
                        .requestMatchers("/api/invoice")
                        .hasRole("CASHIER")

                )
                .userDetailsService(userService)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }







    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserService userService) {
        return new JwtAuthenticationFilter(jwtService, userService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache_Control", "Content-type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




}
