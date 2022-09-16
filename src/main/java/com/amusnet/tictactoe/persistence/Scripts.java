package com.amusnet.tictactoe.persistence;

public class Scripts {

  final static String PERSIST_PLAYER_SCRIPT = """
      INSERT INTO
        leaderboard.players (player_name, victories)
      VALUES
        (?, ?)""";

  final static String UPDATE_PLAYER_SCRIPT = """
       UPDATE
         leaderboard.players
       SET
         victories = ?
      WHERE player_name = ?
       """;

  final static String GET_PLAYER_VICTORIES = """
      SELECT
        victories
      FROM
        leaderboard.players
      WHERE
        player_name = ?;
      """;

  final static String GET_TOP_PLAYERS_SCRIPT = """
      SELECT
        player_name,
        victories
      FROM
        leaderboard.players
      ORDER BY
        victories DESC
      LIMIT
        3
      """;
}
