package payment;

import com.google.inject.Inject;
import communication.Readable;
import java.math.BigDecimal;

public class CoinReceiverDialog {

  private final Readable reader;

  @Inject
  public CoinReceiverDialog(final Readable reader) {
    this.reader = reader;
  }

  public BigDecimal addCurrency() {
    BigDecimal amount = BigDecimal.ZERO;

    final String EXIT_COMMAND = "q";
    boolean finished = false;
    while (!finished) {
      String input = getAmountInput();

      if (input.equals(EXIT_COMMAND)) {
        finished = true;
      } else {
        amount = amount.add(BigDecimal.valueOf(Long.parseLong(input)));
      }
    }

    System.out.println("Thanks, you inserted " + amount + " dollars.");
    return amount;
  }

  private String getAmountInput() {
    System.out.println("Input amount: ");
    return reader.readNextLine();
  }

}
