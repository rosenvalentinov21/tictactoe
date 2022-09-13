package model.game;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import communication.MessageProvider;
import java.util.UUID;
import model.AI.Opponent;
import model.enums.GameState;
import model.enums.Markers;
import model.Player;
import model.grid.GridManager;
import model.leaderboard.HallOfFame;
import org.junit.jupiter.api.Test;
import persistence.PlayerRepository;
import model.view.GameDialog;

class GameManagerTest {

  private final MessageProvider messageProvider = new MessageProvider();
  private final Player player = new Player(UUID.randomUUID().toString().substring(0, 5));
  private final Opponent opponent = mock(Opponent.class);
  private final GameDialog gameDialog = mock(GameDialog.class);
  private final GridManager gridManager = mock(GridManager.class);
  private final PlayerRepository playerRepository = mock(PlayerRepository.class);
  private final HallOfFame hallOfFame = mock(HallOfFame.class);

  private final GameManager gameManager = new GameManager(player, opponent, gridManager,
      gameDialog, messageProvider, playerRepository, hallOfFame);

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
  void startGame_AfterPlayerMove_ShouldAskOpponentForInput() {
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