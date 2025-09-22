package com.assignment.customer.rewards.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
