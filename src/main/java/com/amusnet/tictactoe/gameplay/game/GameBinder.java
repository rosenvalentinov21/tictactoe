package com.amusnet.tictactoe.gameplay.game;

import com.google.inject.PrivateModule;

public class GameBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(GameManager.class).asEagerSingleton();
    bind(GameInitializer.class).asEagerSingleton();

    expose(GameInitializer.class);
  }
}
