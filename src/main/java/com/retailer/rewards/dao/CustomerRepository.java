package com.retailer.rewards.dao;

import com.retailer.rewards.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    public Optional<CustomerEntity> findByCustomerId(Long customerId);
}
