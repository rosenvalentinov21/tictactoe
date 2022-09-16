package com.amusnet.tictactoe.payment;

import com.google.inject.PrivateModule;

public class PaymentBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(CoinReceiver.class).asEagerSingleton();

    expose(CoinReceiver.class);
  }
}
