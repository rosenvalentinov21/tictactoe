package com.amusnet.tictactoe.gameplay.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.amusnet.tictactoe.exception.IllegalMoveException;
import com.amusnet.tictactoe.gameplay.enums.GameState;
import com.amusnet.tictactoe.gameplay.enums.Markers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GridTest {

  private final GridVisualizer gridVisualizer = mock(GridVisualizer.class);
  private final Judge judge = mock(Judge.class);
  private GridManager grid;

  @BeforeEach
  void reinitializeGrid() {
    grid = new GridManager(gridVisualizer, judge);
  }

  @Test
  void whenPlaceMarkerOnEmptyBoard_ShouldPlaceMarkerInArray() {
    final int EXPECTED_POSITION = 4;
    final Markers EXPECTED_MARKER = Markers.O;

    grid.placeMarkerOnBoard(EXPECTED_POSITION, EXPECTED_MARKER);
    final Markers actualMarker = grid.getMarkerOnPosition(EXPECTED_POSITION);
    assertEquals(EXPECTED_MARKER, actualMarker);
  }

  @Test
  void whenPlaceMarkerOnMarkedPosition_ShouldThrowException() {
    final int POSITION = 4;
    final Markers MARKER = Markers.O;
    grid.placeMarkerOnBoard(POSITION, MARKER);

    assertThrows(IllegalMoveException.class,
        () -> grid.placeMarkerOnBoard(POSITION, MARKER));
  }

  @Test
  void whenDisplayGrid_ShouldInvokeVisualizeMethod() {
    grid.displayGrid();
    verify(gridVisualizer).visualizeGrid(any());
  }

  @Test
  void whenGetGridCondition_ShouldReturnExpectedWinCondition() {
    when(judge.checkForWinner(any())).thenReturn(GameState.O);

    final GameState EXPECTED_GAME_STATE = GameState.O;
    final GameState actualGameState = grid.getGameState();
    assertEquals(EXPECTED_GAME_STATE, actualGameState);
  }
}