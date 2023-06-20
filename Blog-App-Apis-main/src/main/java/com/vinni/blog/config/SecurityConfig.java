package com.vinni.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vinni.blog.security.CustomUserDetailService;
import com.vinni.blog.security.JwtAuthenticationEntryPoint;
import com.vinni.blog.security.JwtAuthenticatonFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;
    
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticatonFilter jwtAuthenticatonFilter;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
        authenticationManager = authenticationManagerBuilder.build();
        
        http
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/auth/login").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .authenticationManager(authenticationManager)
            .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            
        http.addFilterBefore(this.jwtAuthenticatonFilter,UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }	
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
        authenticationManager = authenticationManagerBuilder.build();
        return authenticationManager;
//	    return http.getSharedObject(AuthenticationManagerBuilder.class)
//	            .build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
}
