package com.amusnet.tictactoe.communication;

public class MessageProvider implements Messaging {

  @Override
  public void displayMessage(final String message) {
    System.out.println(message);
  }

}
