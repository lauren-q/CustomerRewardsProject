package com.retailer.rewards.unitTest;

import com.retailer.rewards.controller.RewardsController;
import com.retailer.rewards.dao.CustomerRepository;
import com.retailer.rewards.entity.CustomerEntity;
import com.retailer.rewards.service.RewardsService;
import com.retailer.rewards.vo.Rewards;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.anyLong;

public class CustomerRewardsAPIUnitTest {
    @Mock
    RewardsService rewardsService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    public void configMock() {
        MockitoAnnotations.openMocks(this);
        RestAssuredMockMvc.standaloneSetup(new RewardsController(rewardsService, customerRepository));
    }

    @Test
    public void testGetRewardsByCustomerId() {
        Mockito.when(customerRepository.findByCustomerId(anyLong()))
                .thenReturn(Optional.of(new CustomerEntity(1l, "Adam")));
        Mockito.when(rewardsService.getRewardsByCustomerId(anyLong()))
                .thenReturn(new Rewards(1, 40, 90, 0, 130));

        given().accept("application/json").get("rewards/1").peek()
                .then().assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("{\"customerId\":1,\"lastFirstMonthScores\":40,\"lastSecondMonthScores\":90,\"lastThirdMonthScores\":0,\"lastThreeMonthsScores\":130}"));
    }

    @Test
    public void testGetRewardsWithNoneExistCustomer() {
        Mockito.when(customerRepository.findByCustomerId(anyLong()))
                .thenReturn(Optional.empty());
        given().accept("application/json").get("rewards/3").peek()
                .then().assertThat()
                .statusCode(404)
                .body("errorCode",Matchers.equalTo(404));
    }
}
