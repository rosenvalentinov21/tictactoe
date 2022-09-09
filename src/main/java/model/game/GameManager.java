package model.game;

import com.google.inject.Inject;
import communication.MessageProvider;
import exception.IllegalMoveException;
import model.AI.Opponent;
import model.GameState;
import model.Markers;
import model.Player;
import model.grid.GridManager;
import view.GameDialog;

public class GameManager {

  private final Player player;
  private final Opponent opponent;

  private final GridManager gridManager;

  private final GameDialog gameDialog;

  private final MessageProvider messageProvider;

  @Inject
  public GameManager(final Player player, final Opponent opponent, final GridManager gridManager,
      final GameDialog gameDialog, final MessageProvider messageProvider) {
    this.player = player;
    this.opponent = opponent;
    this.gridManager = gridManager;
    this.gameDialog = gameDialog;
    this.messageProvider = messageProvider;
  }

  public void startGame() {
    gameDialog.printIntroduction();
    gridManager.displayGrid();
    Markers turn = player.getMarker();
    GameState gameState = GameState.IN_PROGRESS;
    while (gameState.equals(GameState.IN_PROGRESS)) {
      turn = playTurn(turn);
      gridManager.displayGrid();
      gameState = gridManager.getGameState();
    }

    processGameState(gameState);

    ///TODO display hall of fame
  }

  private Markers playTurn(Markers turn) {
    int opponentMove;
    if (turn.equals(player.getMarker())) {
      try {
        playerMove();
        turn = opponent.getMarker();
      } catch (IllegalMoveException e) {
        messageProvider.displayMessage(e.getMessage());
      }

    } else if (turn.equals(opponent.getMarker())) {
      opponentMove = opponent.makeAMove(gridManager.copyBoard());
      gridManager.placeMarkerOnBoard(opponentMove, opponent.getMarker());
      turn = player.getMarker();
    }
    return turn;
  }

  private void processGameState(GameState gameState) {
    if (gameState.equals(GameState.X)) {
      messageProvider.displayMessage("Congrats, you won!");
      final String playerName = gameDialog.requestPlayerName();
      ///TODO persist Player to DB
    } else if (gameState.equals(GameState.O)) {
      messageProvider.displayMessage("Better luck next time!");
    } else if (gameState.equals(GameState.DRAW)) {
      messageProvider.displayMessage("Uff, you finished draw!");
    }
  }

  private void playerMove() throws IllegalMoveException {
    int playerMove;
    playerMove = gameDialog.requestPlayerMove();
    gridManager.placeMarkerOnBoard(playerMove, player.getMarker());
  }


}
