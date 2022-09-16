package com.amusnet.tictactoe.gameplay.leaderboard;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.amusnet.tictactoe.communication.MessageProvider;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import com.amusnet.tictactoe.persistence.PlayerRepository;

class HallOfFameTest {

  private final PlayerRepository playerRepository = mock(PlayerRepository.class);
  private final HallOfFame hallOfFame = new HallOfFame(playerRepository, new MessageProvider());

  @Test
  void displayTopThreePlayers() throws SQLException {
    hallOfFame.displayTopThreePlayers();

    verify(playerRepository).getTopThreePlayers();
  }
}