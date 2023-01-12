package com.retailer.rewards.exception;

public class CustomerNotFoundException extends RuntimeException{

    private String errorMessage;
    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public CustomerNotFoundException() {
        super();
    }

}
