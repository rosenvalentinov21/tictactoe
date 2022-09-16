package com.amusnet.tictactoe.gameplay.AI;

import com.google.inject.PrivateModule;
import java.util.Random;

public class AIBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(Random.class).asEagerSingleton();
    bind(RandomSlotPositionGenerator.class).asEagerSingleton();
    bind(Opponent.class).asEagerSingleton();

    expose(Opponent.class);
  }
}
