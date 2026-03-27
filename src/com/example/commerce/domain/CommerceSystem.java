package com.example.commerce.domain;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CommerceSystem { // 프로그램 비즈니스 로직
    //커머스 플랫폼의 상품 관리 / 사용자 입력을 처리하기
    // 1. 상품 목록 출력 - 실행하면 여러 전자제품을 출력해보자
    // 2. 제시된 상품 중 입력받은 숫자에 따라 다른 로직 실행

    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
    Product product = new Product("상품이름", 0, "설명", 0);
    Category category = new Category("전자제품");
    List<Product> productList = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    int choice = 0;

    public CommerceSystem() {
        this.categoryList = new ArrayList<>();
    }

    public void start() {
        while (true) {
            int index = 1;
            category.viewCategory();

            System.out.println("categoryList.toString() - "
                    + category.categoryList.toString());
//            [전자제품, 의류, 식품]

            try {
                int input = sc.nextInt();
                if (input == 1) {
                    Category selectedCategory = category.categoryList.get(0);
                    System.out.println("selectedCategory.toString() - "
                            + selectedCategory);
//                    // 전자제품

                    // 전자제품의 상품리스트 어떻게?
                    if (category.categoryList.get(0).equals(selectedCategory)) {
                        // productList 출력
                        System.out.println("[ 실시간 커머스 플랫폼 - " + selectedCategory + " ]");
                        List<Product> productList = selectedCategory.getProductList();
                        System.out.println(productList);
                        for (Product product : productList) {
                        String formattedPrice = formatter.format(product.getPrice());
                        System.out.printf("%d. %-15s | %10s원 | %s%n",
                                index++,
                                product.getItem(),
                                formattedPrice,
                                product.getDescription());
                    }
                    System.out.println("0. 종료");
                    }
                    System.out.println(selectedCategory); // 전자제품
                }


                for(Category category : categoryList) {
                    System.out.println(categoryList.size());
                }
                if (input == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("빈 배열");
            }

//            for(; categoryList.size(); i++ ) {
//                System.out.println(categoryList.get(i));
//            }
//            Category selectedCategory = categoryList.get(input - 1);
//            System.out.println("[ 실시간 커머스 플랫폼 - " + selectedCategory + " ]");


////            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
//            for (Product product : productList) {
//                String formattedPrice = formatter.format(product.getPrice());
//                System.out.printf("%d. %-15s | %10s원 | %s%n",
//                        index++,
//                        product.getItem(),
//                        formattedPrice,
//                        product.getDescription());
//            }
//            System.out.println("0. 종료");
//
//            // else
//
//
//            choice = input;
//            IntStream.range(0, productList.size())
//                    .filter(i -> i == choice - 1)
//                    .mapToObj(productList::get)
//                    .findFirst()
//                    .ifPresentOrElse(
//                            p -> {
//                                String price = formatter.format(p.getPrice());
//                                System.out.printf("선택한 상품: %s | %s원 | %s | 재고: %d개%n",
//                                        p.getItem(),
//                                        price,
//                                        p.getDescription(),
//                                        p.getQuantity());
//                            },
//                            () -> System.out.println("존재하지 않는 상품입니다.")
//                    );
        }
    }
}