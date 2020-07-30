package com.worldnavigator.frontend.model.springdomain.userdao;

import com.worldnavigator.frontend.model.springdomain.domainuser.DomainUser;

public interface DomainUserDAO {
  void createNewUser(DomainUser domainUser);

  DomainUser getDomainUserByUserName(String username);
}
