package com.rev.util;

import net.avalara.avatax.rest.client.AvaTaxClient;
import net.avalara.avatax.rest.client.enums.AvaTaxEnvironment;

public class AvaTaxClientUtil {

  public static final AvaTaxClient getAvaTaxSandboxClient() {
    ConfigUtil configUtil = new ConfigUtil();
    String appName = configUtil.readProperty("app.name");
    String appVersion = configUtil.readProperty("app.version");
    String machineName = configUtil.readProperty("machine.name");
    String username = configUtil.readProperty("user.name");
    String password = configUtil.readProperty("password");

    return new AvaTaxClient(appName, appVersion, machineName, AvaTaxEnvironment.Sandbox)
        .withSecurity(username, password);
  }
}
