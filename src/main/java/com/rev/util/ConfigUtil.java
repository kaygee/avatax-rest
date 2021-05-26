package com.rev.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

  private static final String CONFIG_FILE = "avalara-config.properties";

  public ConfigUtil() {}

  public String readProperty(String keyName) {
    final Properties properties = new Properties();
    try (final FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
      properties.load(fileInputStream);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    return properties.getProperty(keyName, "There is no key in [" + CONFIG_FILE + "]");
  }
}
