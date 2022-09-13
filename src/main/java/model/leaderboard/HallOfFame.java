package model.leaderboard;

import com.google.inject.Inject;
import communication.MessageProvider;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import model.Player;
import persistence.PlayerRepository;

public class HallOfFame {

  private final PlayerRepository playerRepository;
  private final MessageProvider messageProvider;

  @Inject
  public HallOfFame(final PlayerRepository playerRepository,
      final MessageProvider messageProvider) {
    this.playerRepository = playerRepository;
    this.messageProvider = messageProvider;
  }

  private List<Player> getTopThreePlayers() {
    try {
      return playerRepository.getTopThreePlayers();
    } catch (final SQLException e) {
      messageProvider.displayMessage("There was a problem fetching players from DB");
    }
    return Collections.emptyList();
  }

  public void displayTopThreePlayers() {
    final List<Player> topPlayers = getTopThreePlayers();
    int counter = 1;
    validateLeaderboard(topPlayers);

    for (final Player player : topPlayers) {
      messageProvider.displayMessage(counter + ") name: " + player.getName() + " ,"
          + " victories: " + player.getVictories());
      counter++;
    }
  }

  private void validateLeaderboard(final List<Player> topPlayers) {
    if (topPlayers.isEmpty()) {
      messageProvider.displayMessage("There are currently no players in the leaderboard.");
    }
  }
}
