package com.assignment.customer.rewards.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.customer.rewards.model.CustomerRewardsResponse;
import com.assignment.customer.rewards.model.Transaction;
import com.assignment.customer.rewards.service.CustomerRewardService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRewardController {

	@Autowired
    private CustomerRewardService customerRewardService;

    @PostMapping
    public List<CustomerRewardsResponse> calculateRewards(@RequestBody List<Transaction> transactions) {
        return customerRewardService.calculateCustomerRewards(transactions);
    }
    
    @PostMapping("/rewards")
    public ResponseEntity<List<CustomerRewardsResponse>> calculateCustomerRewards(@RequestParam("file") MultipartFile file) throws Exception {
    	List<CustomerRewardsResponse> processedRecords = new ArrayList<>();
    	if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(processedRecords);
        }
        return ResponseEntity.ok(customerRewardService.calculateRewards(file));
    }
}
