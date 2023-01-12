package com.retailer.rewards.service;

import com.retailer.rewards.dao.TransactionRepository;
import com.retailer.rewards.entity.TransactionEntity;
import com.retailer.rewards.util.Constants;
import com.retailer.rewards.vo.Rewards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class RewardsServiceImpl implements RewardsService{


    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Rewards getRewardsByCustomerId(Long customerId) {
        Timestamp lastFirstMonthTimeStamp = getPreviousTimeStamp(Constants.DAYS_PER_MONTH);
        Timestamp lastSecondMonthTimeStamp = getPreviousTimeStamp( Constants.DAYS_PER_MONTH * 2);
        Timestamp lastThirdMonthTimeStamp = getPreviousTimeStamp(Constants.DAYS_PER_MONTH * 3);

        List<TransactionEntity> lastFirstMonthTransactions =
                transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, lastFirstMonthTimeStamp, Timestamp.from(Instant.now()));

        List<TransactionEntity> lastSecondMonthTransactions =
                transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimeStamp, lastFirstMonthTimeStamp);

        List<TransactionEntity> lastThirdMonthTransactions =
                transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimeStamp, lastSecondMonthTimeStamp);

        long lastFirstMonthScores = calculateScoresBasedOnTransactions(lastFirstMonthTransactions);
        long lastSecondMonthScores = calculateScoresBasedOnTransactions(lastSecondMonthTransactions);
        long lastThirdMonthScores = calculateScoresBasedOnTransactions(lastThirdMonthTransactions);

        Rewards customerRewards = new Rewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastFirstMonthScores(lastFirstMonthScores);
        customerRewards.setLastSecondMonthScores(lastSecondMonthScores);
        customerRewards.setLastThirdMonthScores(lastThirdMonthScores);
        customerRewards.setLastThreeMonthsScores(lastFirstMonthScores + lastSecondMonthScores + lastThirdMonthScores);
        return customerRewards;
    }

    private long calculateScoresBasedOnTransactions(List<TransactionEntity> lastFirstMonthTransactions) {
        return lastFirstMonthTransactions.stream().map(tx -> calculateScoresBasedOnOneTransaction(tx)).reduce(0L, Long::sum);
    }

    private long calculateScoresBasedOnOneTransaction(TransactionEntity tx) {
        double transactionAmount = tx.getTransactionAmount();
        long totalScore = 0;
        if (transactionAmount >= Constants.SECOND_REWARD_LEVEL) {
            totalScore += Math.round(transactionAmount - Constants.SECOND_REWARD_LEVEL) * 2;
            transactionAmount -= (transactionAmount - Constants.SECOND_REWARD_LEVEL);
        }
        if (transactionAmount >= Constants.FIRST_REWARD_LEVEL) {
            totalScore += Math.round(transactionAmount - Constants.FIRST_REWARD_LEVEL);
//            transactionAmount -= Constants.FIRST_REWARD_LEVEL;
        }
        return totalScore;
    }

    private Timestamp getPreviousTimeStamp(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

}


