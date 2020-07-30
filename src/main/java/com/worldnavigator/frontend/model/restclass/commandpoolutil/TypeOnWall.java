package com.worldnavigator.frontend.model.restclass.commandpoolutil;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TypeOnWall {
  DOOR("door"),
  SELLER("seller"),
  PAINTING("painting"),
  MIRROR("mirror"),
  CHEST("chest"),
  NOTHING("nothing");

  private final String checkName;

  TypeOnWall(String checkName) {
    this.checkName = checkName;
  }

  public String getCheckName() {
    return checkName;
  }
}
