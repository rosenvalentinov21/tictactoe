package payment;

import java.math.BigDecimal;

public class CoinReceiver {

  private final CoinReceiverDialog coinReceiverDialog;

  public CoinReceiver(CoinReceiverDialog coinReceiverDialog) {
    this.coinReceiverDialog = coinReceiverDialog;
  }

  public BigDecimal receiveCoins(){
    return coinReceiverDialog.addCurrency();
  }
}
