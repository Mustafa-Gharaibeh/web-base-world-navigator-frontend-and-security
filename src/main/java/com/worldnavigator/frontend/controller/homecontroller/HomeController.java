package com.worldnavigator.frontend.controller.homecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping
  public String mainPage() {
    return "main/index";
  }

  // Login form
  @GetMapping("/login")
  public String login() {
    return "main/login";
  }

  // Login form with error
  @GetMapping("/login-error")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "main/login";
  }
}
