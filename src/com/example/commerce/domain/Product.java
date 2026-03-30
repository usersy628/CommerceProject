package com.example.commerce.domain;

import java.util.ArrayList;
import java.util.List;

public class Product { // 개별 상품 정보를 가지는 클래스
    // 속성
    // 상품명, 가격, 설명, 재고수량 필드 분할
    public String item;
    public int price;
    public String description;
    public int quantity;

    // 생성자
    public Product(String item, int price, String description, int quantity) {
        this.item = item;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    // 기능
    // 게터
    public String getItem() {
        return item;
    }
    public int getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getQuantity() {
        return quantity;
    }

    // 세터
    public void setItem(String item) {
        this.item = item;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
