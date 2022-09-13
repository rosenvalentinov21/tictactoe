package payment;

import com.google.inject.Inject;
import communication.MessageProvider;
import communication.Readable;
import java.math.BigDecimal;

public class CoinReceiverDialog {

  private final Readable reader;
  private final MessageProvider messageProvider;

  @Inject
  public CoinReceiverDialog(final Readable reader, final MessageProvider messageProvider) {
    this.reader = reader;
    this.messageProvider = messageProvider;
  }

  public BigDecimal addCurrency() {
    BigDecimal amount = BigDecimal.ZERO;

    final String EXIT_COMMAND = "q";
    messageProvider.displayMessage("Type 'q' to exit.");
    boolean finished = false;
    while (!finished) {
      String input = getAmountInput();

      if (input.equals(EXIT_COMMAND)) {
        finished = true;
      } else {
        amount = addAmount(amount, input);
      }
    }

    System.out.println("Thanks, you inserted " + amount + " dollars.");
    return amount;
  }

  private BigDecimal addAmount(final BigDecimal startAmount, final String input) {
    BigDecimal amount = startAmount;
    try {
      if (Integer.parseInt(input) > 0) {
        amount = startAmount.add(BigDecimal.valueOf(Long.parseLong(input)));
      } else {
        messageProvider.displayMessage("Please enter a positive number.");
      }
    } catch (NumberFormatException ex) {
      messageProvider.displayMessage("Please enter a valid number.");
    }
    return amount;
  }

  private String getAmountInput() {
    System.out.println("Input amount: ");
    return reader.readNextLine();
  }

}
