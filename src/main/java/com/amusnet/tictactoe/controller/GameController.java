package com.amusnet.tictactoe.controller;

import com.google.inject.Inject;
import com.amusnet.tictactoe.gameplay.game.GameInitializer;

public class GameController {

  private final GameInitializer gameInitializer;

  @Inject
  public GameController(final GameInitializer gameInitializer) {
    this.gameInitializer = gameInitializer;
  }

  public void InitializeGame() {
    gameInitializer.initializeGame();
  }
}
