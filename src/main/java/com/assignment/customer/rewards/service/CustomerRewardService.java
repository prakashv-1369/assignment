package com.assignment.customer.rewards.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.customer.rewards.model.CustomerRewardsResponse;
import com.assignment.customer.rewards.model.Transaction;

/**
 * Service class which will handle the business logic
 * for the customer reward points based on the amount spent
 */
@Service
public class CustomerRewardService {

	Logger logger = LoggerFactory.getLogger(CustomerRewardService.class);
	public List<CustomerRewardsResponse> calculateRewards(MultipartFile file) throws Exception {
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			List<Transaction> transactions = reader.lines()
	              .skip(1) // Skip header
	              .map(line -> line.split(","))
	                .map(fields -> new Transaction(
	                        fields[0],
	                        LocalDate.parse(fields[1], inputFormatter),
	                        Double.parseDouble(fields[2])
	                ))
	                .collect(Collectors.toList());
			logger.info("File Transactions list size is : " + transactions.size());
			
			return calculateCustomerRewards(transactions);
				
	    }
	}
	
	public List<CustomerRewardsResponse> calculateCustomerRewards(List<Transaction> transactions) {
		logger.info("Entering the calculateCustomerRewards with Transactions List ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

     //calculate total points per month for the customer
        Map<String, Map<String, Integer>> customerMonthlyPoints = transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::getCustomerId,
                Collectors.groupingBy(
                    tx -> tx.getTransactionDate().format(formatter),
                    Collectors.summingInt(tx -> calculatePoints(tx.getAmount()))
                )
            ));

        logger.info("Creating the CustomerRewardsResponse List with monthly points and total points");
     // Map to List<CustomerRewardsResponse>
        return customerMonthlyPoints.entrySet().stream()
            .map(entry -> {
            	
                String customerId = entry.getKey();
                Map<String, Integer> monthlyPoints = entry.getValue();
                int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();
                return new CustomerRewardsResponse(customerId, monthlyPoints, totalPoints);
                
            }).collect(Collectors.toList());
        
        
    }

	private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
        	// If amount more than 100 then calculate with 2 points
            points += (int) ((amount - 100) * 2) + 50;
        } else if (amount > 50) {
            points += (int) (amount - 50);
        }
        return points;
    }
}
