package com.example.commerce.domain;

import java.util.ArrayList;
import java.util.List;

public class Category { // Product 클래스 관리
    // 속성
    private String categoryName;
    private List<Product> productList = new ArrayList<>();

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


//    public void printProductInfo() {
//        List<Product> productList = selectedCategory.getProductList();
//        for (int i = 0; i < productList.size(); i++) {
//            String formattedPrice = formatter.format(productList.get(i).getPrice());
//            System.out.printf("%d. %-15s | %10s원 | %s%n",
//                    i + 1,
//                    productList.get(i).getItem(),
//                    formattedPrice,
//                    productList.get(i).getDescription());
//        }
//    }
}
