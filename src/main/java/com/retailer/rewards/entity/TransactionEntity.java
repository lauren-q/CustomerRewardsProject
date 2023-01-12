package com.retailer.rewards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name="transaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionEntity {

    @Id
    @Column(name="transaction_id")
    private Long transactionId;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="transaction_date")
    private Timestamp transactionDate;

    @Column(name="amount")
    private Double transactionAmount;
}
