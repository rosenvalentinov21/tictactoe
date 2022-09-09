import com.google.inject.Guice;
import com.google.inject.Injector;
import config.BindingModule;
import java.math.BigDecimal;
import model.game.GameManager;
import payment.CoinReceiver;
import payment.CoinReceiverDialog;

public class Application {

  public static void main(String[] args) {

    final Injector injector = Guice.createInjector(new BindingModule());
    final CoinReceiverDialog coinReceiverDialog = injector.getInstance(CoinReceiverDialog.class);
    final CoinReceiver coinReceiver = new CoinReceiver(coinReceiverDialog);

    final GameManager gameManager = injector.getInstance(GameManager.class);
    gameManager.startGame();

    final BigDecimal amount = coinReceiver.receiveCoins();
    System.out.println(amount);
  }
}
