package com.amusnet.tictactoe.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class MySqlConnectionTest {

  private final MySqlConnection mySqlConnection = new MySqlConnection(
      "jdbc:mysql://localhost:3306/leaderboard",
      "root", "1234");

  @Test
  void getDBConnection() throws SQLException {
    final Connection connection = mySqlConnection.getDBConnection();

    assertNotNull(connection);
  }
}