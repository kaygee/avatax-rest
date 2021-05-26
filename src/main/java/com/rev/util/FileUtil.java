package com.rev.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.avalara.avatax.rest.client.models.TransactionModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FileUtil {

  private static final String TRANSACTIONS_FILENAME = "transactions-results.json";
  private static final String TRANSACTION_ERRORS = "transaction-errors.txt";

  public static void writeErrors(List<String> lines) {
    if (!exists(TRANSACTION_ERRORS)) {
      createFile(TRANSACTION_ERRORS);
    }
    lines.stream().forEach(line -> appendToFile(TRANSACTION_ERRORS, line));
  }

  public static void writeTransactions(List<TransactionModel> transactionModels) {
    if (!exists(TRANSACTIONS_FILENAME)) {
      createFile(TRANSACTIONS_FILENAME);
    }

    try {
      new ObjectMapper().writeValue(new File(TRANSACTIONS_FILENAME), transactionModels);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  public static void appendToFile(String file, String text) {
    try {
      FileWriter fileWriter = new FileWriter(file, true);
      fileWriter.append(text).append("\n");
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException ioe) {
      throw new RuntimeException("Problem reading/writing file [" + ioe.getMessage() + "].");
    }
  }

  public static void createFile(String fileName) {
    try {
      if (!Files.exists(Paths.get(fileName), NOFOLLOW_LINKS)) {
        Files.createFile(Paths.get(fileName));
      }
    } catch (IOException ioe) {
      throw new RuntimeException(
          "Problem determining if file exists or can be created [" + ioe.getMessage() + "].");
    }
  }

  public static boolean exists(String pathname) {
    File file = new File(pathname);
    return file.exists() && !file.isDirectory();
  }
}
