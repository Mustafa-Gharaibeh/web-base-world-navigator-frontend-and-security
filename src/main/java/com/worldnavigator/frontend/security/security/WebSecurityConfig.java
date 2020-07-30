package com.worldnavigator.frontend.security.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final DaoAuthenticationProvider provider;
  private final UserDetailsService userDetailsService;

  public WebSecurityConfig(
      @Qualifier("daoAuthenticationProvider") DaoAuthenticationProvider provider,
      @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
    this.provider = provider;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(provider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/", "/index.html", "/css/*", "/signUp")
        .permitAll()
        .antMatchers("/mazes/***")
        .hasRole(Role.GAMER.name())
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .defaultSuccessUrl("/mazes/")
        .and()
        .rememberMe()
        .key("uniqueAndSecret")
        .userDetailsService(userDetailsService)
        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutRequestMatcher(
            new AntPathRequestMatcher(
                "/logout",
                GET
                    .name())) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID", "remember-me")
        .logoutSuccessUrl("/login");
  }

  private enum Role {
    GAMER
  }
}
