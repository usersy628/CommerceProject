package com.example.commerce;

import com.example.commerce.domain.CommerceSystem;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Commerce!");
        CommerceSystem commerceSystem = new CommerceSystem();
        commerceSystem.start();
    }
}
