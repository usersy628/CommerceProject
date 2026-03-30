package com.example.commerce.domain;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class Category { // Product 클래스 관리
    // List<Product>: CommerceSystem -> Category 클래스가 관리하도록 변경합니다.
    // 속성
    String categoryName;
    List<Product> productList = new ArrayList<>();

    List<Category> categoryList = new ArrayList<>();
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

    public void viewCategory() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        int index = 1;
        for (Category category : categoryList) {
            System.out.printf("%d. %-15s%n",
                    index++,
                    category.getCategoryName());
            }
        }

    public void initData () {
        /* ------ 더미데이터 시작 ------ */
        Category electronics = new Category("전자제품");
        electronics.addProduct(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 20));
        electronics.addProduct(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 15));
        electronics.addProduct(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 12));
        electronics.addProduct(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 2));

        Category clothes = new Category("의류");
        clothes.addProduct(new Product("후드티", 55000, "기본 후드티", 3));
        clothes.addProduct(new Product("반바지", 55000, "기본 반바지", 8));

        Category food = new Category("식품");
        food.addProduct(new Product("우유", 2000, "신선한 우유", 10));
        food.addProduct(new Product("자갈치", 1500, "추억의 과자", 16));

        categoryList.add(electronics);
        categoryList.add(clothes);
        categoryList.add(food);
        /* ------ 더미데이터 끝 ------ */
    }
        @Override
        public String toString() {
            return categoryName;
        }

        public List<Product> viewProductList(Category selectedCategory) {
            System.out.println("[ 실시간 커머스 플랫폼 - " + selectedCategory.categoryName + " ]");
            List<Product> selectedProductList = selectedCategory.getProductList();
            for (int i = 0; i < selectedProductList.size(); i++) {
                Product product = selectedProductList.get(i);
                String formattedPrice = formatter.format(product.getPrice());

                System.out.printf("%d. %-15s | %10s원 | %s%n",
                        i + 1,
                        product.getItem(),
                        formattedPrice,
                        product.getDescription());
            }
            return selectedProductList;
        }

        public Product viewProduct(Product product) {
            if (product == null) {
                System.out.println("상품이 없습니다.");
            }
            String price = formatter.format(product.getPrice());
            System.out.printf("선택한 상품: %s | %s원 | %s | 재고: %d개%n",
                    product.getItem(),
                    price,
                    product.getDescription(),
                    product.getQuantity());
            return product;
        }
}
