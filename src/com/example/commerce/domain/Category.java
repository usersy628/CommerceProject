package com.example.commerce.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Category { // Product 클래스 관리
    // List<Product>: CommerceSystem -> Category 클래스가 관리하도록 변경합니다.
    // 속성
    String categoryName;
    List<Product> productList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();

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

    public void viewCategory() {
//        categoryList.add(new Category("전자제품"));
//        productList.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 20));
        Category electronics = new Category("전자제품");
        electronics.addProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 20));
        electronics.addProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 15));
        electronics.addProduct(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 12));
        electronics.addProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 2));

//        categoryList.add(new Category("의류"));
        Category clothes = new Category("의류");
        clothes.addProduct(new Product("후드티", 55000, "기본 후드티", 3));
        clothes.addProduct(new Product("반바지", 55000, "기본 반바지", 8));

//        categoryList.add(new Category("식품"));
        Category food = new Category("식품");
        food.addProduct(new Product("우유", 2000, "신선한 우유", 10));
        food.addProduct(new Product("자갈치", 1500, "추억의 과자", 16));

        categoryList.add(electronics);
        categoryList.add(clothes);
        categoryList.add(food);

        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        System.out.println(">>");

        for (Category category : categoryList) {
            System.out.println(category.getCategoryName());
        }
    }
}
