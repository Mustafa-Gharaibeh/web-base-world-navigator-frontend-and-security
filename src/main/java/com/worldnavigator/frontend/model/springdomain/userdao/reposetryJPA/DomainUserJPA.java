package com.worldnavigator.frontend.model.springdomain.userdao.reposetryJPA;

import com.worldnavigator.frontend.model.springdomain.domainuser.DomainUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DomainUserJPA extends JpaRepository<DomainUser, Integer> {
  public Optional<DomainUser> getUserByUserName(String username);
}
