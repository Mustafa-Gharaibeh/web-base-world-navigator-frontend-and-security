package com.worldnavigator.frontend.controller.startcontroller.startgamemapping;

import com.worldnavigator.frontend.model.restclass.commandpoolutil.CommandPoolUtil;
import com.worldnavigator.frontend.model.restclass.mazelistwrapper.MazeListWrapper;
import com.worldnavigator.frontend.model.restclass.restcommunicationbackend.RestCommunicationBackEnd;
import com.worldnavigator.frontend.model.restclass.restcommunicationbackend.RestCommunicationBackEndBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/mazes")
public class StartMapping {
  private final RestTemplate restTemplate;

  public StartMapping(RestTemplate restTemplate, HttpHeaders headers) {
    this.restTemplate = restTemplate;
  }

  @PostMapping("/start")
  public String setMaze(final String command, Principal principal, Model model) {
    final String gamerName = principal.getName();
    final String commands = "commands";
    final String output = "output";
    MazeListWrapper mazeList =
        restTemplate.getForObject(
            "http://world-navigator-pool/mazes/mazeList", MazeListWrapper.class);
    RestCommunicationBackEndBuilder backEndBuilder = new RestCommunicationBackEndBuilder();
    try {
      if (Objects.requireNonNull(mazeList).getMazeList().contains(command.trim())) {
        backEndBuilder.setGamerName(gamerName);
        backEndBuilder.setMazeName(command.trim());
        RestCommunicationBackEnd backEnd = backEndBuilder.createRestCommunicationBackEnd();
        HttpEntity<RestCommunicationBackEnd> req = new HttpEntity<>(backEnd);
        RestCommunicationBackEnd backEnd1 =
            restTemplate.postForObject(
                "http://world-navigator-pool/mazes/register", req, RestCommunicationBackEnd.class);

        model.addAttribute(
            "commandListName", Objects.requireNonNull(backEnd1).getCommandListName().trim());
        model.addAttribute(
            commands,
            CommandPoolUtil.commandList(
                Objects.requireNonNull(backEnd1).getCommandListName().trim()));
        model.addAttribute(output, backEnd1.getCommunicationVariable());
      } else {
        return "redirect:/mazes/";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    model.addAttribute("input");

    return "action/action";
  }
}
