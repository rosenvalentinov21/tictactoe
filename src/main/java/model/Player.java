package model;

import model.enums.Markers;

public class Player {

  private final Markers marker = Markers.X;
  private String name;
  private Long victories;

  public Player() {
  }

  public Player(final String name) {
    this.name = name;
  }

  public Long getVictories() {
    return victories;
  }

  public void setVictories(final Long victories) {
    this.victories = victories;
  }

  public Markers getMarker() {
    return marker;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
