Project Description:
    - A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
    Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.

Main application:
    - Technologies used - Spring Boot, Spring Data JPA
    - Embedded database Server - H2
    - Start the application by running CustomerRewardProjectApplication class
    - Configuration file - application.properties
    - data.sql and schema.sql will be automatically populated during startup
    - set Logger as info level
    - .gitignore added
    - deployed on AWS EC2 Instance
        http://52.53.225.63:8009/rewards/1
        http://52.53.225.63:8009/rewards/2
        http://52.53.225.63:8009/rewards/3
    - Designed UI for customer Rewards Calculator    
        http://52.53.225.63:8009/
    - use Lombok to simplify develop process

Test:
    - Technologies used - Spring Test, JUnit, Mockito,REST Assured
    - unit test and integration test for both existing customer and non-existing customer

Todo:
    - For Simplicity, no security. We may add a basic http authentication later.
    - Jenkins integration / Email Notification
    - Added Trigger for Jenkins










