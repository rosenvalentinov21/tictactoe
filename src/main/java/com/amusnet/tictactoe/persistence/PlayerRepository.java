package com.amusnet.tictactoe.persistence;

import static com.amusnet.tictactoe.persistence.Scripts.GET_PLAYER_VICTORIES;
import static com.amusnet.tictactoe.persistence.Scripts.GET_TOP_PLAYERS_SCRIPT;
import static com.amusnet.tictactoe.persistence.Scripts.PERSIST_PLAYER_SCRIPT;
import static com.amusnet.tictactoe.persistence.Scripts.UPDATE_PLAYER_SCRIPT;

import com.amusnet.tictactoe.communication.MessageProvider;
import com.amusnet.tictactoe.gameplay.player.Player;
import com.google.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {

  private final MySqlConnection mySqlConnection;
  private final MessageProvider messageProvider;

  @Inject
  public PlayerRepository(final MySqlConnection mySqlConnection,
      final MessageProvider messageProvider) {
    this.mySqlConnection = mySqlConnection;
    this.messageProvider = messageProvider;
  }

  private Connection getConnection() {
    try {
      return mySqlConnection.getDBConnection();
    } catch (final SQLException e) {
      messageProvider.displayMessage(
          "There was a problem establishing a connection with database!");
    }
    return null;
  }


  public void savePlayerInDB(final Player player) throws SQLException {
    final Connection connection = getConnection();

    if (connection != null) {
      final long victories = getPlayerVictories(player.getName(), connection);
      if (victories > 0) {
        updatePlayerDataInDB(player, connection, victories);
      } else if (victories == 0) {
        PersistPlayerToDBInitially(player, connection, victories);
      }
    }
  }

  private void updatePlayerDataInDB(final Player player, final Connection connection,
      final long victories) throws SQLException {

    try (final PreparedStatement updatePlayerData = connection.prepareStatement(
        UPDATE_PLAYER_SCRIPT)) {
      updatePlayerData.setLong(1, victories + 1);
      updatePlayerData.setString(2, player.getName());

      updatePlayerData.executeUpdate();
    }
  }

  private void PersistPlayerToDBInitially(final Player player, final Connection connection,
      final long victories) throws SQLException {

    try (final PreparedStatement persistPlayerData = connection.prepareStatement(
        PERSIST_PLAYER_SCRIPT)) {
      persistPlayerData.setString(1, player.getName());
      persistPlayerData.setLong(2, victories + 1);

      persistPlayerData.executeUpdate();
    }
  }

  private long getPlayerVictories(final String playerName, final Connection connection) {

    final Long result = fetchVictoriesFromDB(playerName, connection);
    if (result != null) {
      return result;
    }
    return 0L;
  }

  private Long fetchVictoriesFromDB(final String playerName, final Connection connection) {

    try (final PreparedStatement getPlayerVictories =
        connection.prepareStatement(GET_PLAYER_VICTORIES)) {
      getPlayerVictories.setString(1, playerName);
      final ResultSet result = getPlayerVictories.executeQuery();
      if (result.next()) {
        return result.getLong("victories");
      }
    } catch (final SQLException e) {
      messageProvider.displayMessage("There was a problem fetching player data from database.");
    }
    return null;
  }


  public List<Player> getTopThreePlayers() throws SQLException {

    final List<Player> players = new ArrayList<>();
    final Connection connection = getConnection();
    if (connection != null) {
      getTopThreePlayersFromDB(players, connection);
    }
    return players;
  }

  private void getTopThreePlayersFromDB(final List<Player> players, final Connection connection)
      throws SQLException {
    try (final PreparedStatement preparedStatement = connection.prepareStatement(
        GET_TOP_PLAYERS_SCRIPT)) {
      final ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        final Player player = new Player(resultSet.getString("player_name"));
        player.setVictories(resultSet.getLong("victories"));

        players.add(player);
      }
    }
  }
}
