package com.amusnet.tictactoe.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JdbcProperties {

  public Properties getProperties() {
    final String path = "src/main/resources/application.properties";
    try (final FileInputStream fis = new FileInputStream(path)) {
      final Properties props = new Properties();
      props.load(fis);
      return props;
    } catch (final IOException e) {
      System.out.println("There was a problem reading properties from file!");
    }
    return null;
  }
}
