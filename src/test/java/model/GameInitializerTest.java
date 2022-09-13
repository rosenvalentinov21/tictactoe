package model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import communication.MessageProvider;
import communication.Readable;
import communication.Reader;
import java.math.BigDecimal;
import model.game.GameManager;
import org.junit.jupiter.api.Test;
import payment.CoinReceiver;
import persistence.MySqlConnection;

class GameInitializerTest {

  private final CoinReceiver coinReceiver = mock(CoinReceiver.class);
  private final GameManager gameManager = mock(GameManager.class);
  private final MessageProvider messageProvider = mock(MessageProvider.class);
  private final Readable reader = mock(Reader.class);
  private final MySqlConnection mySqlConnection = mock(MySqlConnection.class);

  private final GameInitializer gameInitializer = new GameInitializer(coinReceiver,
      gameManager, messageProvider, reader, mySqlConnection);

  @Test
  void initializeGame_WhenPutEnoughMoneyShouldStartGame() {
    final BigDecimal ENOUGH_MONEY = BigDecimal.valueOf(100);
    when(coinReceiver.receiveCoins()).thenReturn(ENOUGH_MONEY);
    final String EXIT_COMMAND = "q";
    when(reader.readNextLine()).thenReturn(EXIT_COMMAND);

    gameInitializer.initializeGame();

    verify(gameManager).startGame();
  }
}