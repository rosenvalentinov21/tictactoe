package com.amusnet.tictactoe.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amusnet.tictactoe.communication.MessageProvider;
import com.amusnet.tictactoe.communication.Reader;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import com.amusnet.tictactoe.view.CoinReceiverDialog;

class CoinReceiverDialogTest {

  private final Reader reader = mock(Reader.class);
  private final MessageProvider messageProvider = mock(MessageProvider.class);

  private final CoinReceiverDialog coinReceiverDialog = new CoinReceiverDialog(reader,
      messageProvider);

  @Test
  void whenAddCurrency_ShouldReturnTheAmount() {
    final String EXPECTED_VALUE = String.valueOf(BigDecimal.TEN);
    final String EXIT_COMMAND = "q";
    when(reader.readNextLine()).thenReturn(EXPECTED_VALUE, EXIT_COMMAND);

    final BigDecimal actualValue = coinReceiverDialog.addCurrency();
    assertEquals(EXPECTED_VALUE, String.valueOf(actualValue));
  }
}