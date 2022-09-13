package model;

import com.google.inject.Inject;
import communication.MessageProvider;
import communication.Messaging;
import communication.Readable;
import java.math.BigDecimal;
import java.sql.SQLException;
import model.game.GameManager;
import payment.CoinReceiver;
import persistence.MySqlConnection;

public class GameInitializer {

  private final CoinReceiver coinReceiver;
  private final GameManager gameManager;
  private final Messaging messageProvider;
  private final Readable reader;
  private final MySqlConnection mySqlConnection;

  private final BigDecimal GAME_PRICE = BigDecimal.TEN;

  @Inject
  public GameInitializer(final CoinReceiver coinReceiver, final GameManager gameManager,
      final MessageProvider messageProvider, final Readable reader,
      final MySqlConnection mySqlConnection) {
    this.coinReceiver = coinReceiver;
    this.gameManager = gameManager;
    this.messageProvider = messageProvider;
    this.reader = reader;
    this.mySqlConnection = mySqlConnection;
  }

  public void initializeGame() {
    boolean proceed = true;

    while (proceed) {
      acceptPayment();
      gameManager.startGame();

      proceed = checkForExitCondition();
    }
    closeDBConnection();
  }

  private void closeDBConnection() {
    try {
      mySqlConnection.closeConnection();
    } catch (SQLException ex) {
      messageProvider.displayMessage("There was a problem closing DB connection!");
    }
  }

  private boolean checkForExitCondition() {
    messageProvider.displayMessage("Type 'q' to exit or press any key to proceed.");
    final String exitCommand = reader.readNextLine();

    return !exitCommand.equals("q");
  }

  private void acceptPayment() {
    BigDecimal amount = BigDecimal.ZERO;
    while (amount.compareTo(GAME_PRICE) < 0) {
      amount = amount.add(coinReceiver.receiveCoins());
      if (amount.compareTo(GAME_PRICE) < 0) {
        messageProvider.displayMessage("Please enter enough money to proceed.");
      }
    }
  }
}
