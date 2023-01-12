package com.retailer.rewards.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rewards {
    private long customerId;
    private long lastFirstMonthScores;
    private long lastSecondMonthScores;
    private long lastThirdMonthScores;
    private long lastThreeMonthsScores;
}
