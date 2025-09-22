package com.assignment.customer.rewards.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Request parameters from the transaction file
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

	private String customerId;
    private LocalDate transactionDate;
    private double amount;
}
