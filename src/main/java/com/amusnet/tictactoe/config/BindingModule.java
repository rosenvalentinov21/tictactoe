package com.amusnet.tictactoe.config;

import com.amusnet.tictactoe.communication.CommunicationBinder;
import com.amusnet.tictactoe.controller.ControllerBinder;
import com.amusnet.tictactoe.gameplay.AI.AIBinder;
import com.amusnet.tictactoe.gameplay.game.GameBinder;
import com.amusnet.tictactoe.gameplay.grid.GridBinder;
import com.amusnet.tictactoe.gameplay.leaderboard.LeaderboardBinder;
import com.amusnet.tictactoe.gameplay.player.PlayerBinder;
import com.amusnet.tictactoe.payment.PaymentBinder;
import com.amusnet.tictactoe.persistence.PersistenceBinder;
import com.amusnet.tictactoe.view.ViewBinder;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class BindingModule extends AbstractModule {

  private final JdbcProperties jdbcProperties = new JdbcProperties();

  @Override
  protected void configure() {
    install(new CommunicationBinder());
    install(new PaymentBinder());
    install(new AIBinder());
    install(new PlayerBinder());
    install(new GridBinder());
    install(new ViewBinder());
    install(new PersistenceBinder());
    install(new LeaderboardBinder());
    install(new GameBinder());
    install(new ControllerBinder());
    Names.bindProperties(binder(), jdbcProperties.getProperties());
  }
}
