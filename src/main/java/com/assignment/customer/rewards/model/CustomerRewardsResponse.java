package com.assignment.customer.rewards.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Response object hold the customerId, monthly points and total points
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRewardsResponse {

	private String customerId;
	private Map<String, Integer> monthlyPoints;
	private int totalPoints;
}
