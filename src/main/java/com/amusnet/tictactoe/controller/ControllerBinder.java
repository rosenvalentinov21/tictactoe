package com.amusnet.tictactoe.controller;

import com.google.inject.PrivateModule;

public class ControllerBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(GameController.class).asEagerSingleton();

    expose(GameController.class);
  }
}
