package com.worldnavigator.frontend.model.springdomain.userdao.db;

import com.worldnavigator.frontend.model.springdomain.domainuser.DomainUser;
import com.worldnavigator.frontend.model.springdomain.userdao.DomainUserDAO;
import com.worldnavigator.frontend.model.springdomain.userdao.reposetryJPA.DomainUserJPA;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository("DB")
public class DomainUserDaoDBImpl implements DomainUserDAO {

  private final DomainUserJPA domainUserJPA;

  public DomainUserDaoDBImpl(DomainUserJPA domainUserJPA) {
    this.domainUserJPA = domainUserJPA;
  }

  @Override
  public void createNewUser(DomainUser domainUser) {
    domainUserJPA.save(domainUser);
  }

  @Override
  public DomainUser getDomainUserByUserName(String username) throws UsernameNotFoundException {
    return domainUserJPA
        .getUserByUserName(username)
        .orElseThrow(
            () -> new UsernameNotFoundException(String.format("%s is Not Exist", username)));
  }
}
