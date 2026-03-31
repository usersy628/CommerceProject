package com.example.commerce.domain;

import java.text.NumberFormat;
import java.util.Locale;

public class Product { // 개별 상품 정보를 가지는 클래스
    // 속성
    // 상품명, 가격, 설명, 재고수량 필드 분할
    private String item;
    private int price;
    private String description;
    private int stock;
    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);


    // 생성자
    public Product(String item, int price, String description, int stock) {
        this.item = item;
        this.price = price;
        this.description = description;
        this.stock = stock;
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
    public int getStock() {
        return stock;
    }

    // 세터
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getItem() + " " + getPrice() + " " + getDescription() + " " + getStock();
    }

    public void printSelectedProduct() {
        String formattedPrice = formatter.format(getPrice());
        System.out.printf("선택한 상품: %s | %s원 | %s | 재고: %d개%n",
                getItem(),
                formattedPrice,
                getDescription(),
                getStock());
    }
}
