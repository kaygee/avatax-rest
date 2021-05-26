package com.rev.util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.rev.beans.InputRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AvalaraRecordUtil {

  private static final String INPUT_SAMPLE_CSV = "./resources/input-sample.csv";
  private static final List<InputRecord> AVALARA_RECORDS = getAvalaraRecords();

  public static List<InputRecord> getByZipCode(String zipCode) {
    Predicate<InputRecord> predicate =
            inputRecord -> inputRecord.getZipCode().equals(zipCode);
    return getMatchingBy(predicate);
  }

  public static List<InputRecord> getByState(String state) {
    Predicate<InputRecord> predicate = inputRecord -> inputRecord.getState().equals(state);
    return getMatchingBy(predicate);
  }

  private static List<InputRecord> getMatchingBy(Predicate<InputRecord> predicate) {
    return AVALARA_RECORDS.stream().filter(predicate).collect(Collectors.toList());
  }

  private static List<InputRecord> getAvalaraRecords() {
    try {
      return new CsvToBeanBuilder(Files.newBufferedReader(Paths.get(INPUT_SAMPLE_CSV), UTF_8))
          .withType(InputRecord.class)
          .build()
          .parse();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
