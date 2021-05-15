package com.rev;

import net.avalara.avatax.rest.client.AvaTaxClient;
import net.avalara.avatax.rest.client.TransactionBuilder;
import net.avalara.avatax.rest.client.enums.AvaTaxEnvironment;
import net.avalara.avatax.rest.client.enums.DocumentType;
import net.avalara.avatax.rest.client.enums.TransactionAddressType;
import net.avalara.avatax.rest.client.models.PingResultModel;
import net.avalara.avatax.rest.client.models.TransactionModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class AvaTaxRestClient {

  protected static final Logger LOG = LoggerFactory.getLogger(AvaTaxRestClient.class);

  @Test
  public void canDoSomething() {
    LOG.info("Starting...");
    // creates our AvaTaxClient
    AvaTaxClient client =
        new AvaTaxClient("Rev", "1.0", "localhost", AvaTaxEnvironment.Sandbox)
            .withSecurity("MyUsername", "MyPassword");

    try {
      // verify that we can ping successfully
      PingResultModel ping = client.ping();
      if (ping.getAuthenticated()) {
        System.out.print("Successfully created a client!");
      }
    } catch (Exception e) {
    }

    // builds the Transaction and creates the TransactionModel
    try {
      TransactionModel transaction =
          new TransactionBuilder(client, "DEFAULT", DocumentType.SalesInvoice, "ABC")
              .withAddress(
                  TransactionAddressType.SingleLocation,
                  "123 Main Street",
                  null,
                  null,
                  "Irvine",
                  "CA",
                  "92615",
                  "US")
              .withLine(new BigDecimal(100.0), new BigDecimal(1), "P0000000")
              .Create();
    } catch (Exception e) {
    }
    LOG.info("Stopping...");
  }
}
