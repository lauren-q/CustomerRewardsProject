package com.retailer.rewards.controller;

import com.retailer.rewards.dao.CustomerRepository;
import com.retailer.rewards.entity.CustomerEntity;
import com.retailer.rewards.exception.CustomerNotFoundException;
import com.retailer.rewards.service.RewardsService;
import com.retailer.rewards.vo.ErrorResponse;
import com.retailer.rewards.vo.Rewards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RewardsController {

    private static Logger logger = LoggerFactory.getLogger(RewardsController.class);

    RewardsService rewardsService;
    CustomerRepository customerRepository;

    @Autowired
    public RewardsController(RewardsService rewardsService, CustomerRepository customerRepository) {
      this.rewardsService = rewardsService;
      this.customerRepository = customerRepository;
    }

    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId) {
        CustomerEntity customerEntity = customerRepository.findByCustomerId(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("customer doesn't exist"));
        Rewards customerRewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards, HttpStatusCode.valueOf(200));

    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerCustomerNotFound(Exception ex) {
        logger.error("Cannot find customer");
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
