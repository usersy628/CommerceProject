package com.example.commerce.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer { // 고객관리
    private String customerName;
    private String customerEmail;
    private String customerGrade;
    private List<Product> cartList = new ArrayList<>();

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
