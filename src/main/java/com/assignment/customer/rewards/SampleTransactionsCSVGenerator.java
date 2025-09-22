package com.assignment.customer.rewards;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * This file will generate the sample customer transactions
 * With Columns customerId,transactionDate,amount
 */
public class SampleTransactionsCSVGenerator {
	static Logger logger = LoggerFactory.getLogger(SampleTransactionsCSVGenerator.class);

	public static void main(String[] args) {
        String fileName = "customer_transactions.csv";
        int recordCount = 1000;
        int customerCount = 250; // Number of distinct customers
        Random random = new Random();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (FileWriter writer = new FileWriter(fileName)) {
            // Write CSV header
            writer.write("customerId,transactionDate,amount\n");

            for (int i = 1; i <= recordCount; i++) {
                // Reuse a CustomerID from a limited pool
                int customerId = 10000 + random.nextInt(customerCount);

                // Generate random transaction amount
                double amount = 50 + (50 + random.nextInt(10));

                // Generate random date within past 3 months
                LocalDate endDate = LocalDate.now();
                LocalDate startDate = endDate.minusMonths(2);
                long daysBetween = endDate.toEpochDay() - startDate.toEpochDay();
                LocalDate randomDate = startDate.plusDays(random.nextInt((int) daysBetween + 1));

                // Write CSV line
                writer.write(customerId + "," +  randomDate.format(dateFormatter) +"," + String.format("%.2f", amount)+ "\n");
            }

            logger.info("CSV file created successfully: " + fileName);

        } catch (IOException e) {
        	logger.error("Error writing CSV file: " + e.getMessage(), e);
        }
    }
}
