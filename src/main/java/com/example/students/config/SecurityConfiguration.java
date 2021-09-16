package com.example.students.config;

import com.example.students.security.JwtConfigure;
import com.example.students.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin(maxAge = 3600L)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private final UserDetailsService userDetailsService;
  private final JwtTokenProvider jwtTokenProvider;

  public SecurityConfiguration(UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
    this.userDetailsService = userDetailsService;
    this.jwtTokenProvider = jwtTokenProvider;
  }


  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors().disable()
        .headers().frameOptions().disable()
        .and()
        .httpBasic()
        .and()
        .authorizeRequests()
//        .antMatchers("/user/**").hasRole("USER")
//        .antMatchers("/api/getAllStudents").hasRole("USER")
        .antMatchers("/user/login").permitAll()
        .antMatchers("/swagger-ui.html", "/webjars/**", "/swagger-resources", "/swagger-resources/**", "/v2/**", "/csrf").permitAll()
        .antMatchers("/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .apply(new JwtConfigure(jwtTokenProvider))
    ;
  }

}
