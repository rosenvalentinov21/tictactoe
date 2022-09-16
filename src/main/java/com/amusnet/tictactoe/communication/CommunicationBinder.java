package com.amusnet.tictactoe.communication;

import com.google.inject.PrivateModule;
import java.util.Scanner;

public class CommunicationBinder extends PrivateModule {

  @Override
  protected void configure() {
    bind(Scanner.class).toInstance(new Scanner(System.in));
    bind(Readable.class).to(Reader.class).asEagerSingleton();
    bind(Messaging.class).to(MessageProvider.class).asEagerSingleton();

    expose(Readable.class);
    expose(Messaging.class);
  }
}
