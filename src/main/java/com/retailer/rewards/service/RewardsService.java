package com.retailer.rewards.service;

import com.retailer.rewards.vo.Rewards;


public interface RewardsService {
    public Rewards getRewardsByCustomerId(Long customerId);
}
