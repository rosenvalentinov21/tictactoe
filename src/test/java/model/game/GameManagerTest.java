package model.game;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import communication.MessageProvider;
import java.util.UUID;
import model.AI.Opponent;
import model.GameState;
import model.Markers;
import model.Player;
import model.grid.GridManager;
import org.junit.jupiter.api.Test;
import view.GameDialog;

class GameManagerTest {

  private final MessageProvider messageProvider = new MessageProvider();
  private final Player player = new Player();
  private final Opponent opponent = mock(Opponent.class);
  private final GameDialog gameDialog = mock(GameDialog.class);
  private final GridManager gridManager = mock(GridManager.class);

  private final GameManager gameManager = new GameManager(player, opponent, gridManager,
      gameDialog, messageProvider);

  @Test
  void startGame_WhenGameStateGetsWinner_ShouldAskForPlayerName() {
    when(gridManager.getGameState()).thenReturn(GameState.X);
    when(gameDialog.requestPlayerName()).thenReturn(UUID.randomUUID().toString());

    gameManager.startGame();

    verify(gameDialog).requestPlayerName();
  }

  @Test
  void startGame_WhenHasWinner_ShouldHaveAskedPlayerForInput() {
    when(gridManager.getGameState()).thenReturn(GameState.X);

    gameManager.startGame();

    verify(gameDialog).requestPlayerMove();
  }

  @Test
  void startGame_AfterPlayerMove_ShouldAskOpponentForInput(){
    when(gridManager.getGameState()).thenReturn(GameState.IN_PROGRESS,
        GameState.X);
    when(opponent.getMarker()).thenReturn(Markers.O);

    gameManager.startGame();
    verify(opponent).makeAMove(any());
  }

  @Test
  void startGame_WhileGameInProgress_ShouldHaveAskedForInputEveryRound() {
    when(gridManager.getGameState()).thenReturn(GameState.IN_PROGRESS, GameState.IN_PROGRESS,
        GameState.X);
    when(opponent.getMarker()).thenReturn(Markers.O);

    gameManager.startGame();

    verify(gameDialog, times(2)).requestPlayerMove();
    verify(opponent).makeAMove(any());
  }
}