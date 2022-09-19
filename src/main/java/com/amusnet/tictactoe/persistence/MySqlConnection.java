package com.amusnet.tictactoe.persistence;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlConnection {

  private Connection connection;
  private final String url;
  private final String username;
  private final String password;

  @Inject
  public MySqlConnection(@Named("URL") final String url,
      @Named("username") final String username, @Named("password") final String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  @Inject
  public Connection getDBConnection() throws SQLException {
    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    connection = DriverManager.getConnection(
        url, username, password);
    return connection;
  }

  public void closeConnection() throws SQLException {
    connection.close();
  }

}
