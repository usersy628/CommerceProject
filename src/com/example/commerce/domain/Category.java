package com.example.commerce.domain;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Category { // Product 클래스 관리
    // 속성
    private String categoryName;
    private List<Product> productList = new ArrayList<>();

    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);

    // 생성자
    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.productList = new ArrayList<>();
    }

    // 기능
    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProductList () {
        return productList;
    }

    public void addProduct (Product product) {
        productList.add(product);
    }

    @Override
    public String toString() {
        return categoryName;
    }

    // 상품리스트 출력
    public void printProductInfo() {
        System.out.println("[ 실시간 커머스 플랫폼 - " + getCategoryName() + " ]");
        for (int i = 0; i < productList.size(); i++) {
            String formattedPrice = formatter.format(productList.get(i).getPrice());
            System.out.printf("%d. %-15s | %10s원 | %s%n",
                    i + 1,
                    productList.get(i).getItem(),
                    formattedPrice,
                    productList.get(i).getDescription());
        }
        System.out.println("0. 뒤로가기");
    }


}
