package com.amusnet.tictactoe.gameplay.leaderboard;

import com.google.inject.PrivateModule;

public class LeaderboardBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(HallOfFame.class).asEagerSingleton();

    expose(HallOfFame.class);
  }
}
