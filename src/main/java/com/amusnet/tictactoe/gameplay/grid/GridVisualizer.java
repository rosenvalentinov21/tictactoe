package com.amusnet.tictactoe.gameplay.grid;

import com.google.inject.Inject;
import com.amusnet.tictactoe.communication.MessageProvider;
import com.amusnet.tictactoe.gameplay.enums.Markers;

public class GridVisualizer {

  private final MessageProvider messageProvider;

  @Inject
  public GridVisualizer(final MessageProvider messageProvider) {
    this.messageProvider = messageProvider;
  }

  public void visualizeGrid(final Markers[] board) {
    displayRow(board[0], board[1], board[2]);
    displayRow(board[3], board[4], board[5]);
    displayRow(board[6], board[7], board[8]);
    messageProvider.displayMessage("-------------------");
  }

  private void displayRow(final Markers marker1, final Markers marker2,
      final Markers marker3) {
    String row = "";
    row += getMarkerRepresentation(marker1);
    row += getMarkerRepresentation(marker2);
    row += getMarkerRepresentation(marker3);

    messageProvider.displayMessage(row);
  }

  private String getMarkerRepresentation(final Markers marker) {
    if (marker == null) {
      return " " + null + " ";
    } else {
      return "  " + marker + "  ";
    }
  }
}
