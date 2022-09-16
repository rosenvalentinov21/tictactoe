package com.amusnet.tictactoe.gameplay.grid;

import com.google.inject.PrivateModule;

public class GridBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(GridManager.class).asEagerSingleton();
    bind(GridVisualizer.class).asEagerSingleton();
    bind(Judge.class).asEagerSingleton();

    expose(GridManager.class);
  }
}
