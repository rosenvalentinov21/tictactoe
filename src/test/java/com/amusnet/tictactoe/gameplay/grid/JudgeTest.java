package com.amusnet.tictactoe.gameplay.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.amusnet.tictactoe.gameplay.enums.GameState;
import com.amusnet.tictactoe.gameplay.enums.Markers;
import org.junit.jupiter.api.Test;

class JudgeTest {


  private final Judge judge = new Judge();

  @Test
  void checkForWinner_WhenThereIsWinCondition_ShouldReturnWinnerState() {
    final Markers[] board = new Markers[9];

    createWinCondition(board);

    final GameState EXPECTED_GAME_STATE = GameState.O;
    final GameState actualGameState = judge.checkForWinner(board);
    assertEquals(EXPECTED_GAME_STATE, actualGameState);
  }

  @Test
  void checkForDraw_WhenThereIsDrawCondition_ShouldReturnDrawState() {
    final Markers[] board = new Markers[9];

    createDrawCondition(board);

    final GameState EXPECTED_GAME_STATE = GameState.DRAW;
    final GameState actualGameState = judge.checkForWinner(board);
    assertEquals(EXPECTED_GAME_STATE, actualGameState);
  }

  @Test
  void checkForInProgress_WhenGameIsInProgress_ShouldReturnInProgress() {
    final Markers[] board = new Markers[9];

    final GameState EXPECTED_GAME_STATE = GameState.IN_PROGRESS;
    final GameState actualGameState = judge.checkForWinner(board);
    assertEquals(EXPECTED_GAME_STATE, actualGameState);
  }

  private void createWinCondition(final Markers[] board) {
    board[0] = Markers.O;
    board[1] = Markers.O;
    board[2] = Markers.O;
  }

  private void createDrawCondition(final Markers[] board) {
    board[0] = Markers.O;
    board[1] = Markers.X;
    board[2] = Markers.O;

    board[3] = Markers.O;
    board[4] = Markers.X;
    board[5] = Markers.X;

    board[6] = Markers.X;
    board[7] = Markers.O;
    board[8] = Markers.X;
  }
}