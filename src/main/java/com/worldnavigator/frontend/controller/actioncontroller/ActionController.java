package com.worldnavigator.frontend.controller.actioncontroller;

import com.worldnavigator.frontend.model.restclass.commandpoolutil.CommandPoolUtil;
import com.worldnavigator.frontend.model.restclass.restcommunicationbackend.RestCommunicationBackEnd;
import com.worldnavigator.frontend.model.restclass.restcommunicationbackend.RestCommunicationBackEndBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/mazes")
public class ActionController {

  private final RestTemplate template;

  public ActionController(RestTemplate template) {
    this.template = template;
  }

  @PostMapping("/action")
  public String action(final String input, Principal principal, Model model) {
    final String actionPage = "action/action";
    final String commands = "commands";
    final String output = "output";
    final String gamerName = principal.getName();
    RestCommunicationBackEndBuilder backEndBuilder = new RestCommunicationBackEndBuilder();
    RestCommunicationBackEnd backEnd =
        backEndBuilder
            .setCommunicationVariable(input.trim())
            .setGamerName(gamerName)
            .setCommandListName((String) model.getAttribute("commandListName"))
            .createRestCommunicationBackEnd();
    HttpEntity<RestCommunicationBackEnd> entity = new HttpEntity<>(backEnd);
    RestCommunicationBackEnd backEnd1 =
        template.postForObject(
            "http://world-navigator-pool/mazes/action", entity, RestCommunicationBackEnd.class);

    model.addAttribute(
        "commandListName", Objects.requireNonNull(backEnd1).getCommandListName().trim());
    model.addAttribute(
        commands,
        CommandPoolUtil.commandList(Objects.requireNonNull(backEnd1).getCommandListName().trim()));
    model.addAttribute(output, backEnd1.getCommunicationVariable());

    return actionPage;
  }
}
