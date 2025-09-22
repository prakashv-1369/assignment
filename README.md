# Simple Spring Boot with Java 8#

## Project Description

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.  
  
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction. 
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points). 
  
Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total. 

## Solution
### Calculate reward points from a CSV file

- ** MultipartFile upload (CSV with transactions)**

- ** Spring Boot (REST API)**

- ** Business logic for reward calculation**

- ** JUnit 5**

- ** REST endpoint: POST /api/customer/rewards**


### Sample CSV File Generation

 You can update the SampleTransactionCSVGeneration.java with required number of records

 You can also find customer_transaction.csv file in the project with 1000 records


## Building the Project

To build the project using Maven, navigate to the project root directory in your terminal and execute:

```bash
mvn clean install
```

The application will be accessible at http://localhost:8080/

localhost:8080/api/customer/rewards

<img width="939" height="314" alt="image" src="https://github.com/user-attachments/assets/0cf726ef-5609-4d79-baf0-7087154d37eb" />



