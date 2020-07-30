package com.worldnavigator.frontend.security.security.daoauthenticationprovider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DaoAuthenticationProviderConfig {
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;

  public DaoAuthenticationProviderConfig(
      @Qualifier("BCryptPasswordEncoder") PasswordEncoder passwordEncoder,
      @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
  }

  @Bean("daoAuthenticationProvider")
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }
}
