package com.rev;

import com.rev.util.AvaTaxClientUtil;
import com.rev.util.FileUtil;
import net.avalara.avatax.rest.client.AvaTaxClient;
import net.avalara.avatax.rest.client.TransactionBuilder;
import net.avalara.avatax.rest.client.enums.DocumentType;
import net.avalara.avatax.rest.client.enums.TransactionAddressType;
import net.avalara.avatax.rest.client.models.PingResultModel;
import net.avalara.avatax.rest.client.models.TransactionModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Fail.fail;

public class AvaTaxTransactionsFromUat {

  private static final Logger LOGGER = LoggerFactory.getLogger(AvaTaxTransactionsFromUat.class);

  private static final AvaTaxClient AVA_TAX_CLIENT = AvaTaxClientUtil.getAvaTaxSandboxClient();
  private static final List<TransactionModel> AVALARA_RESULTS = new ArrayList();
  private static final List<String> AVALARA_ERRORS = new ArrayList();

  @BeforeAll
  public static void checkAvalaraStatusBeforeStartingSubmission() {
    try {
      PingResultModel ping = AVA_TAX_CLIENT.ping();
      if (ping.getAuthenticated()) {
        LOGGER.info("Successfully authenticated.");
      }
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @AfterAll
  public static void writeResultsFileToJson() {
    LOGGER.info("Writing [" + AVALARA_RESULTS.size() + "] AvaTax Transactions.");
    FileUtil.writeTransactions(AVALARA_RESULTS);
  }

  @AfterAll
  public static void writeErrorsToTextFile() {
    LOGGER.info("Writing [" + AVALARA_ERRORS.size() + "] AvaTax errors.");
    FileUtil.writeErrors(AVALARA_ERRORS);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/uat-input-sample.csv", numLinesToSkip = 1)
  public void canCreateTransactionAndWriteResult(
      String streetAddress,
      String city,
      String state,
      String zipCode,
      String country,
      String amount,
      String quantity,
      String taxCode) {
    TransactionModel transaction = null;
    try {
      transaction =
          new TransactionBuilder(AVA_TAX_CLIENT, "REV", DocumentType.SalesInvoice, "ABC")
              .withAddress(
                  TransactionAddressType.SingleLocation,
                  streetAddress,
                  null,
                  null,
                  city,
                  state,
                  zipCode,
                  country)
              .withLine(new BigDecimal(amount), new BigDecimal(quantity), taxCode)
              .Create();
    } catch (Exception e) {
      AVALARA_ERRORS.add(e.getMessage());
    }
    AVALARA_RESULTS.add(transaction);
  }
}
