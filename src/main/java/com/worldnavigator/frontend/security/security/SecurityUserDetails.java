package com.worldnavigator.frontend.security.security;

import com.worldnavigator.frontend.model.springdomain.domainuser.DomainUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

public class SecurityUserDetails implements UserDetails {

  private final DomainUser domainUser;
  private final PasswordEncoder passwordEncoder;

  public SecurityUserDetails(DomainUser domainUser) {
    this.domainUser = domainUser;
    passwordEncoder = new BCryptPasswordEncoder(5);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority =
        new SimpleGrantedAuthority(String.format("ROLE_%s", domainUser.getRole()));
    return Collections.singletonList(authority);
  }

  @Override
  public String getPassword() {
    return passwordEncoder.encode(domainUser.getPassword());
  }

  @Override
  public String getUsername() {
    return domainUser.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return domainUser.isEnabled();
  }
}
