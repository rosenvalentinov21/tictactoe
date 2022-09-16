package com.amusnet.tictactoe.gameplay.grid;

import com.google.inject.Inject;
import com.amusnet.tictactoe.exception.IllegalMoveException;
import java.util.Arrays;
import com.amusnet.tictactoe.gameplay.enums.GameState;
import com.amusnet.tictactoe.gameplay.enums.Markers;

public class GridManager {

  private final Markers[] board;
  private final GridVisualizer gridVisualizer;
  private final Judge judge;

  @Inject
  public GridManager(final GridVisualizer gridVisualizer, final Judge judge) {
    this.board = new Markers[9];
    this.gridVisualizer = gridVisualizer;
    this.judge = judge;
  }

  public void placeMarkerOnBoard(final int position, final Markers marker)
      throws IllegalMoveException {
    if (board[position] == null) {
      board[position] = marker;
    } else {
      throw new IllegalMoveException("Position is already taken.");
    }
  }

  public Markers getMarkerOnPosition(final int position) {
    return board[position];
  }

  public void displayGrid() {
    gridVisualizer.visualizeGrid(board);
  }

  public GameState getGameState() {
    return judge.checkForWinner(board);
  }

  public Markers[] copyBoard() {
    return Arrays.copyOf(board, board.length);
  }
}
