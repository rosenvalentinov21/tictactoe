package com.amusnet.tictactoe.gameplay.AI;

import com.google.inject.Inject;
import com.amusnet.tictactoe.communication.MessageProvider;
import com.amusnet.tictactoe.gameplay.enums.Markers;

public class Opponent {

  private final Markers marker = Markers.O;
  private final RandomSlotPositionGenerator randomSlotPositionGenerator;
  private final MessageProvider messageProvider;

  @Inject
  public Opponent(final RandomSlotPositionGenerator randomSlotPositionGenerator,
      final MessageProvider messageProvider) {
    this.randomSlotPositionGenerator = randomSlotPositionGenerator;
    this.messageProvider = messageProvider;
  }


  public int makeAMove(final Markers[] board) {
    int randomInt;
    simulateDelay();

    while (true) {
      randomInt = randomSlotPositionGenerator.generateRandomSlotPosition();
      if (board[randomInt] == null) {
        return randomInt;
      }
    }
  }

  private void simulateDelay() {
    try {
      final long DELAY_TIME = 2000L;
      Thread.sleep(DELAY_TIME);
    } catch (final InterruptedException e) {
      messageProvider.displayMessage(e.getMessage());
    }
  }

  public Markers getMarker() {
    return marker;
  }
}
