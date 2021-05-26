package com.rev.beans;

import com.opencsv.bean.CsvBindByName;
import com.rev.util.AvalaraRecordUtil;

import java.util.List;

public class InputRecord {

  @CsvBindByName(column = "StreetAddress")
  private String streetAddress;

  @CsvBindByName(column = "City")
  private String city;

  @CsvBindByName(column = "State")
  private String state;

  @CsvBindByName(column = "ZipCode")
  private String zipCode;

  @CsvBindByName(column = "Country")
  private String country;

  public static List<InputRecord> filteredByZipCode(String zipCode) {
    return AvalaraRecordUtil.getByZipCode(zipCode);
  }

  public static List<InputRecord> filteredByState(String state) {
    return AvalaraRecordUtil.getByState(state);
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "AvalaraRecord{"
        + "streetAddress='"
        + streetAddress
        + '\''
        + ", city='"
        + city
        + '\''
        + ", state='"
        + state
        + '\''
        + ", zipCode='"
        + zipCode
        + '\''
        + ", country='"
        + country
        + '\''
        + '}';
  }
}
