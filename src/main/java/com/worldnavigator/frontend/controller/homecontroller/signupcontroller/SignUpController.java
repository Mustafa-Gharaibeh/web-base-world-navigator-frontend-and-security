package com.worldnavigator.frontend.controller.homecontroller.signupcontroller;

import com.worldnavigator.frontend.model.springdomain.domainuser.DomainUser;
import com.worldnavigator.frontend.model.springdomain.userdao.DomainUserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

  private final DomainUserDAO domainUserDAO;

  public SignUpController(@Qualifier("DB") DomainUserDAO domainUserDAO) {
    this.domainUserDAO = domainUserDAO;
  }

  @GetMapping("/signUp")
  public String signUpForm(Model model) {
    DomainUser domainUser = new DomainUser();
    model.addAttribute("newUser", domainUser);
    return "main/signUp";
  }

  @PostMapping("/signUp")
  public String createAccount(DomainUser newDomainUser) {
    newDomainUser.setEnabled(true);
    newDomainUser.setRole("GAMER");
    domainUserDAO.createNewUser(newDomainUser);
    return "redirect:/login";
  }
}
