import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import payment.CoinReceiver;
import payment.CoinReceiverDialog;

class CoinReceiverTest {

  private final CoinReceiverDialog mockCoinReceiverDialog = mock(CoinReceiverDialog.class);

  private final CoinReceiver coinReceiver = new CoinReceiver(mockCoinReceiverDialog);

  @Test
  void whenReceiveCoins_ShouldReturnExpectedAmount() {
    final BigDecimal expectedAmount = BigDecimal.TEN;
    when(mockCoinReceiverDialog.addCurrency()).thenReturn(expectedAmount);

    final var actualAmount = coinReceiver.receiveCoins();
    assertEquals(expectedAmount, actualAmount);
  }


}