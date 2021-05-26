# avatax-rest

## Problem

You have a CSV file of inputs you'd like to submit to the AvaTax Sandbox and save the results.

## Configuration

- Avalara AvaTax results CSV in `./resources/input-sample.csv`
- A properties file named `avalara-config.properties`.

## Execution

- From command line `mvn clean -Dtest=AvaTaxTransactionsFromUat test`
