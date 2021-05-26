package com.rev;

import com.rev.beans.InputRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AvaTaxRecords {

  @Test
  public void canBeFilteredByState() {
    List<InputRecord> stateRecords = InputRecord.filteredByState("GA");
    assertThat(stateRecords).as("State Records").hasSize(1);
  }

  @Test
  public void canBeFilteredByZipCode() {
    List<InputRecord> zipCodeRecords = InputRecord.filteredByZipCode("98101");
    assertThat(zipCodeRecords).as("Zip Code Records").hasSize(1);
  }
}
