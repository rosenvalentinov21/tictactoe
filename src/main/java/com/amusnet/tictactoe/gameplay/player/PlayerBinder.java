package com.amusnet.tictactoe.gameplay.player;

import com.google.inject.PrivateModule;

public class PlayerBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(Player.class).asEagerSingleton();
    expose(Player.class);
  }
}
