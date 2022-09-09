package model.AI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import communication.MessageProvider;
import model.Markers;
import org.junit.jupiter.api.Test;

class OpponentTest {

  private final Markers MARKER = Markers.O;

  private final RandomSlotPositionGenerator randomSlotPositionGenerator = mock(
      RandomSlotPositionGenerator.class);

  private final Opponent opponent = new Opponent(randomSlotPositionGenerator, new MessageProvider());

  @Test
  void makeAMove_WhenEncounterEmptyPositionReturnPosition() {
    final int EXPECTED_POSITION = 1;
    when(randomSlotPositionGenerator.generateRandomSlotPosition()).thenReturn(EXPECTED_POSITION);

    final Markers[] board = new Markers[9];

    final int actualPosition = opponent.makeAMove(board);
    assertEquals(EXPECTED_POSITION, actualPosition);
  }

  @Test
  void makeAMove_WhenFirstPositionIsTakenShouldPlaceAtSecond() {
    final int FIRST_POSITION = 1;
    final int SECOND_POSITION = 5;
    when(randomSlotPositionGenerator.generateRandomSlotPosition()).thenReturn(FIRST_POSITION,
        SECOND_POSITION);

    final Markers[] board = new Markers[9];
    board[1] = MARKER;

    final int actualPosition = opponent.makeAMove(board);
    assertEquals(SECOND_POSITION, actualPosition);
  }
}