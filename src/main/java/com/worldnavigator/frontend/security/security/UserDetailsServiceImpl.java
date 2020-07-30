package com.worldnavigator.frontend.security.security;

import com.worldnavigator.frontend.model.springdomain.domainuser.DomainUser;
import com.worldnavigator.frontend.model.springdomain.userdao.DomainUserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final DomainUserDAO domainUserDAO;

  public UserDetailsServiceImpl(DomainUserDAO domainUserDAO) {
    this.domainUserDAO = domainUserDAO;
  }

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    DomainUser user = domainUserDAO.getDomainUserByUserName(s);
    return new SecurityUserDetails(user);
  }
}
