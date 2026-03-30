package com.example.commerce.domain;

public class Customer { // 고객관리
    String customerName;
    String customerEmail;
    String customerGrade;

    public Customer(String customerName, String customerEmail, String customerGrade) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerGrade = customerGrade;
    }

    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public String getCustomerGrade() {
        return customerGrade;
    }

}
