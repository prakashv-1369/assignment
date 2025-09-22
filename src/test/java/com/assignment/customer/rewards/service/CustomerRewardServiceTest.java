package com.assignment.customer.rewards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.assignment.customer.rewards.model.CustomerRewardsResponse;
import com.assignment.customer.rewards.model.Transaction;

public class CustomerRewardServiceTest {

	private final CustomerRewardService customerRewardService = new CustomerRewardService();

	@Test
	void testCalculateRewards() {
		List<Transaction> transactions = Arrays.asList(new Transaction("CUST1", LocalDate.of(2025, 9, 1), 120),
				new Transaction("CUST1", LocalDate.of(2025, 9, 10), 80),
				new Transaction("CUST2", LocalDate.of(2025, 8, 20), 180));

		List<CustomerRewardsResponse> result = customerRewardService.calculateCustomerRewards(transactions);

		assertEquals(2, result.size());

		CustomerRewardsResponse cust1 = result.stream().filter(r -> r.getCustomerId().equals("CUST1")).findFirst().orElse(null);
		assertNotNull(cust1);
		assertEquals(120, cust1.getTotalPoints());
		assertEquals(120, cust1.getMonthlyPoints().get("2025-09").intValue());

		CustomerRewardsResponse cust2 = result.stream().filter(r -> r.getCustomerId().equals("CUST2")).findFirst().orElse(null);
		assertNotNull(cust2);
		assertEquals(210, cust2.getTotalPoints());
		assertEquals(210, cust2.getMonthlyPoints().get("2025-08").intValue());
	}
	
	@Test
	void testNegativeRewards() {
		List<Transaction> transactions = Arrays.asList(new Transaction("CUST1", LocalDate.of(2025, 9, 1), 120),
				new Transaction("CUST1", LocalDate.of(2025, 9, 10), 80),
				new Transaction("CUST2", LocalDate.of(2025, 8, 20), 180));

		List<CustomerRewardsResponse> result = customerRewardService.calculateCustomerRewards(transactions);

		assertEquals(2, result.size());

		CustomerRewardsResponse cust1 = result.stream().filter(r -> r.getCustomerId().equals("CUST1")).findFirst().orElse(null);
		assertNotNull(cust1);
		assertNotEquals(130, cust1.getTotalPoints());
		assertNotEquals(130, cust1.getMonthlyPoints().get("2025-09").intValue());

		CustomerRewardsResponse cust2 = result.stream().filter(r -> r.getCustomerId().equals("CUST2")).findFirst().orElse(null);
		assertNotNull(cust2);
		assertNotEquals(220, cust2.getTotalPoints());
		assertNotEquals(220, cust2.getMonthlyPoints().get("2025-08").intValue());
	}
	
	@Test
	void testInvalidCustomer() {
		List<Transaction> transactions = Arrays.asList(new Transaction("CUST1", LocalDate.of(2025, 9, 1), 120),
				new Transaction("CUST1", LocalDate.of(2025, 9, 10), 80),
				new Transaction("CUST2", LocalDate.of(2025, 8, 20), 180));

		List<CustomerRewardsResponse> result = customerRewardService.calculateCustomerRewards(transactions);

		assertEquals(2, result.size());

		CustomerRewardsResponse cust3 = result.stream().filter(r -> r.getCustomerId().equals("CUST3")).findFirst().orElse(null);
		assertNull(cust3);
	}

}
