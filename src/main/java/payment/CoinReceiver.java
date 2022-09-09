package payment;

import com.google.inject.Inject;
import java.math.BigDecimal;

public class CoinReceiver {

  private final CoinReceiverDialog coinReceiverDialog;

  @Inject
  public CoinReceiver(final CoinReceiverDialog coinReceiverDialog) {
    this.coinReceiverDialog = coinReceiverDialog;
  }

  public BigDecimal receiveCoins() {
    return coinReceiverDialog.addCurrency();
  }
}
