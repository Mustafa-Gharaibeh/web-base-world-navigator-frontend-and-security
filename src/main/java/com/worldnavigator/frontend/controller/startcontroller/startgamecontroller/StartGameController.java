package com.worldnavigator.frontend.controller.startcontroller.startgamecontroller;

import com.worldnavigator.frontend.model.restclass.mazelistwrapper.MazeListWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/mazes")
public class StartGameController {

  private final RestTemplate restTemplate;

  public StartGameController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/")
  public String getMazes(Principal principal, Model model) {
    MazeListWrapper mazeList =
        restTemplate.getForObject("http://world-navigator-pool/mazes/mazeList", MazeListWrapper.class);
    model.addAttribute("name", principal.getName());
    model.addAttribute("mazes", Objects.requireNonNull(mazeList).getMazeList());
    String command = "command";
    model.addAttribute("command", command);
    return "mazes/mazesListing";
  }
}
