package com.amusnet.tictactoe.communication;

import com.google.inject.Inject;
import java.util.Scanner;

public class Reader implements Readable {

  private final Scanner scanner;

  @Inject
  public Reader(final Scanner scanner) {
    this.scanner = scanner;
  }


  @Override
  public String readNextLine() {
    return scanner.nextLine();
  }

}
