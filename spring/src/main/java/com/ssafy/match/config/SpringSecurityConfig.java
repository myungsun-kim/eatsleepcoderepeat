//package com.ssafy.match.config;
//
//import java.util.Collections;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
////                .authorizeRequests()
////                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // - (1)
////                .and()
//            /* 중략 */
////                .anyRequest().authenticated().and()
//            .cors().disable()
//            .csrf().disable()
//            .formLogin().disable()
//            .headers().frameOptions().disable();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        // - (3)
//        configuration.addAllowedOrigin("*");
////        configuration.addAllowedOrigin("http://localhost");
////        configuration.addAllowedOrigin("https://59.151.220.195:5501");
//        // above origin is for the test @ daebalprime local.
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
////        configuration.setAllowCredentials(false);
////        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//}