package model.AI;

import com.google.inject.Inject;
import communication.MessageProvider;
import model.Markers;

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
    while (true) {
      randomInt = randomSlotPositionGenerator.generateRandomSlotPosition();
      try {
        if (board[randomInt] == null) {
          Thread.sleep(2000);
          return randomInt;
        }
      } catch (InterruptedException e) {
        messageProvider.displayMessage(e.getMessage());
      }
    }
  }

  public Markers getMarker() {
    return marker;
  }
}
