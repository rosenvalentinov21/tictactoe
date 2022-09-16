package com.amusnet.tictactoe.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

  private Connection connection;

  public Connection getDBConnection() throws SQLException {
    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    final String url = "jdbc:mysql://localhost:3306/leaderboard";
    final String username = "root";
    final String password = "1234";
    connection = DriverManager.getConnection(
        url, username, password);
    return connection;
  }

  public void closeConnection() throws SQLException {
    connection.close();
  }


}
