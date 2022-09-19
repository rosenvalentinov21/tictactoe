package com.amusnet.tictactoe.gameplay.game;

import com.google.inject.Inject;
import com.amusnet.tictactoe.communication.MessageProvider;
import com.amusnet.tictactoe.exception.IllegalMoveException;
import java.sql.SQLException;
import com.amusnet.tictactoe.gameplay.AI.Opponent;
import com.amusnet.tictactoe.gameplay.player.Player;
import com.amusnet.tictactoe.gameplay.enums.GameState;
import com.amusnet.tictactoe.gameplay.enums.Markers;
import com.amusnet.tictactoe.gameplay.grid.GridManager;
import com.amusnet.tictactoe.gameplay.leaderboard.HallOfFame;
import com.amusnet.tictactoe.view.GameDialog;
import com.amusnet.tictactoe.persistence.PlayerRepository;

public class GameManager {

  private final Player player;
  private final Opponent opponent;

  private final GridManager gridManager;

  private final GameDialog gameDialog;

  private final MessageProvider messageProvider;

  private final PlayerRepository playerRepository;
  private final HallOfFame hallOfFame;

  @Inject
  public GameManager(final Player player, final Opponent opponent, final GridManager gridManager,
      final GameDialog gameDialog, final MessageProvider messageProvider,
      final PlayerRepository playerRepository, final HallOfFame hallOfFame) {
    this.player = player;
    this.opponent = opponent;
    this.gridManager = gridManager;
    this.gameDialog = gameDialog;
    this.messageProvider = messageProvider;
    this.playerRepository = playerRepository;
    this.hallOfFame = hallOfFame;
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

    processGameStateOnFinish(gameState);
    hallOfFame.displayTopThreePlayers();
    gridManager.resetBoard();
  }

  private Markers playTurn(final Markers turn) {
    Markers nextTurn = turn;
    if (turn.equals(player.getMarker())) {
      nextTurn = playerMove();
    } else if (turn.equals(opponent.getMarker())) {
      nextTurn = executeOpponentMove();
    }
    return nextTurn;
  }

  private Markers executeOpponentMove() {
    messageProvider.displayMessage("It`s opponent`s move...");
    final int opponentMove = opponent.makeAMove(gridManager.copyBoard());
    gridManager.placeMarkerOnBoard(opponentMove, opponent.getMarker());
    return Markers.X;
  }

  private void processGameStateOnFinish(final GameState gameState) {
    if (gameState.equals(GameState.X)) {
      messageProvider.displayMessage("Congrats, you won!");
      savePlayerInDB();
    } else if (gameState.equals(GameState.O)) {
      messageProvider.displayMessage("Better luck next time!");
    } else if (gameState.equals(GameState.DRAW)) {
      messageProvider.displayMessage("Uff, you finished draw!");
    }
  }

  private void savePlayerInDB() {
    final String playerName = gameDialog.requestPlayerName();
    player.setName(playerName);
    try {
      playerRepository.savePlayerInDB(player);
    } catch (final SQLException ex) {
      messageProvider.displayMessage(
          "There was a problem with com.amusnet.tictactoe.persistence to db");
    }
  }

  private Markers playerMove() {
    final int playerMove = gameDialog.requestPlayerMove();
    try {
      gridManager.placeMarkerOnBoard(playerMove, player.getMarker());
      return Markers.O;
    } catch (final IllegalMoveException e) {
      messageProvider.displayMessage("You cannot place your marker there.");
      return Markers.X;
    }
  }

}
