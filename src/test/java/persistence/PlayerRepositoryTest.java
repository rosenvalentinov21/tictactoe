package persistence;

import communication.MessageProvider;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import model.Player;
import org.junit.jupiter.api.Test;

class PlayerRepositoryTest {

  private final MessageProvider messageProvider = new MessageProvider();
  private final MySqlConnection mySqlConnection = new MySqlConnection();
  private final PlayerRepository playerRepository = new PlayerRepository(mySqlConnection,
      messageProvider);

  @Test
  void savePlayerInDB() throws SQLException {
    final Player player = new Player();
    player.setName(UUID.randomUUID().toString().substring(0, 5));

    playerRepository.savePlayerInDB(player);
  }

  @Test
  void updatePlayer_whenExists_ShouldAddOneToVictories() throws SQLException {
    final Player player = new Player();
    final String PLAYER_NAME = UUID.randomUUID().toString().substring(0, 5);
    player.setName(PLAYER_NAME);
    playerRepository.savePlayerInDB(player);

    playerRepository.savePlayerInDB(player);
  }

  @Test
  void getTopThreePlayers_ShouldReturnPlayers() throws SQLException {
    for (int i = 0; i <= 5; i++) {
      final Player player = new Player();
      final String PLAYER_NAME = UUID.randomUUID().toString().substring(0, 5);
      player.setName(PLAYER_NAME);

      for (int j = 0; j <= 4; j++) {
        playerRepository.savePlayerInDB(player);
      }
    }

    final List<Player> players = playerRepository.getTopThreePlayers();
    for (Player player : players) {
      System.out.println(player.getVictories());
    }
  }
}