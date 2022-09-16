package com.amusnet.tictactoe.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.amusnet.tictactoe.communication.MessageProvider;
import com.amusnet.tictactoe.communication.Readable;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class GameDialogTest {

  private final Readable reader = mock(Readable.class);
  private final GameDialog gameDialog = new GameDialog(new MessageProvider(), reader);

  @Test
  void requestPlayerMove_WhenValidInputShouldReturnInput() {
    final String INPUT = "5";
    final int EXPECTED_OUTPUT = 4;
    when(reader.readNextLine()).thenReturn(INPUT);

    final int actualValue = gameDialog.requestPlayerMove();
    assertEquals(EXPECTED_OUTPUT, actualValue);
  }

  @Test
  void requestPlayerMove_WhenFirstInputIsInvalidShouldRequestNewAndReturn() {
    final String INVALID_INPUT = "100";
    final String VALID_INPUT = "5";
    final int EXPECTED_OUTPUT = 4;
    when(reader.readNextLine()).thenReturn(INVALID_INPUT, VALID_INPUT);

    final int actualValue = gameDialog.requestPlayerMove();
    assertEquals(EXPECTED_OUTPUT, actualValue);
  }

  @Test
  void requestPlayerName_WhenGivenNameShouldReturnTheName() {
    final String EXPECTED_NAME = UUID.randomUUID().toString();
    when(reader.readNextLine()).thenReturn(EXPECTED_NAME);

    final String actualName = gameDialog.requestPlayerName();
    assertEquals(EXPECTED_NAME, actualName);
  }

}