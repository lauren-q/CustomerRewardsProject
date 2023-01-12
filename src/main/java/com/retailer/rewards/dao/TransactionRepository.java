package com.retailer.rewards.dao;

import com.retailer.rewards.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    public List<TransactionEntity> findAllByCustomerId(Long customerId);
    public List<TransactionEntity> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from, Timestamp to);
}
