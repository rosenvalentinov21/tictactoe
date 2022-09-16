package com.amusnet.tictactoe.persistence;

import com.google.inject.PrivateModule;

public class PersistenceBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(MySqlConnection.class).asEagerSingleton();
    bind(PlayerRepository.class).asEagerSingleton();

    expose(PlayerRepository.class);
  }
}
