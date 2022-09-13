package controller;

import com.google.inject.Inject;
import model.GameInitializer;

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
