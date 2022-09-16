package com.amusnet.tictactoe.gameplay.grid;

import com.amusnet.tictactoe.gameplay.enums.GameState;
import com.amusnet.tictactoe.gameplay.enums.Markers;

public class Judge {

  public GameState checkForWinner(final Markers[] board) {
    for (int position = 0; position < board.length; position++) {
      String line = checkWinCombination(board, position);

      final GameState winner = getWinnerIfExistent(line);
      if (winner != null) {
        return winner;
      }
    }

    return checkForNonWinningGameState(board);
  }

  private String checkWinCombination(final Markers[] board,final int position) {
    return switch (position) {
      case 0 -> stringifyMarkers(board[0], board[1], board[2]);
      case 1 -> stringifyMarkers(board[3], board[4], board[5]);
      case 2 -> stringifyMarkers(board[6], board[7], board[8]);
      case 3 -> stringifyMarkers(board[0], board[3], board[6]);
      case 4 -> stringifyMarkers(board[1], board[4], board[7]);
      case 5 -> stringifyMarkers(board[2], board[5], board[8]);
      case 6 -> stringifyMarkers(board[0], board[4], board[8]);
      case 7 -> stringifyMarkers(board[2], board[4], board[6]);
      default -> null;
    };
  }

  private GameState checkForNonWinningGameState(final Markers[] board) {
    for (int position = 0; position <= board.length; position++) {
      if (board[position] == null) {
        break;
      } else if (position == 8) {
        return GameState.DRAW;
      }
    }
    return GameState.IN_PROGRESS;
  }

  private GameState getWinnerIfExistent(final String line) {
    if (line != null && line.equals("XXX")) {
      return GameState.X;
    } else if (line != null && line.equals("OOO")) {
      return GameState.O;
    }
    return null;
  }

  private String stringifyMarkers(final Markers marker1, final Markers marker2,
      final Markers marker3) {
    String line = "";
    line = concatMarkerIfNotNull(line, marker1);
    line = concatMarkerIfNotNull(line, marker2);
    line = concatMarkerIfNotNull(line, marker3);

    return line;
  }

  private String concatMarkerIfNotNull(final String line, final Markers marker) {
    if (marker != null) {
      return line.concat(String.valueOf(marker));
    } else {
      return line;
    }
  }
}





