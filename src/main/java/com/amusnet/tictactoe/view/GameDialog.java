package com.amusnet.tictactoe.view;

import com.google.inject.Inject;
import com.amusnet.tictactoe.communication.Messaging;
import com.amusnet.tictactoe.communication.Readable;
import java.util.InputMismatchException;

public class GameDialog {

  private final Messaging messageProvider;
  private final Readable reader;

  @Inject
  public GameDialog(final Messaging messageProvider, final Readable reader) {
    this.messageProvider = messageProvider;
    this.reader = reader;
  }


  public void printIntroduction() {
    messageProvider.displayMessage("Welcome to 3x3 tic-tac-toe.");
    messageProvider.displayMessage("Slot places are form 1 to 9.");
    messageProvider.displayMessage("You will start as X");
  }

  public int requestPlayerMove() {
    while (true) {
      messageProvider.displayMessage("Enter a slot number to place your marker: ");
      int position;
      position = readUserInput();

      if (validatePosition(position)) {
        return position - 1;
      } else {
        messageProvider.displayMessage("Please enter a valid position between 1 and 9.");
      }
    }
  }

  private int readUserInput() {
    int position;
    try {
      position = Integer.parseInt(reader.readNextLine());
    } catch (InputMismatchException e) {
      position = 0;
    } catch (NumberFormatException e) {
      position = 0;
      messageProvider.displayMessage("Please enter a valid number.");
    }
    return position;
  }

  public String requestPlayerName() {
    messageProvider.displayMessage("Please enter your name: ");
    return reader.readNextLine();
  }

  private boolean validatePosition(final int position) {
    return position >= 1 && position <= 9;
  }

}
