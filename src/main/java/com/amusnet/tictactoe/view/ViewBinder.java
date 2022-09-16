package com.amusnet.tictactoe.view;

import com.google.inject.PrivateModule;

public class ViewBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(GameDialog.class).asEagerSingleton();
    bind(CoinReceiverDialog.class).asEagerSingleton();

    expose(GameDialog.class);
    expose(CoinReceiverDialog.class);
  }
}
