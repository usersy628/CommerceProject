package com.example.commerce;

import com.example.commerce.domain.Product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Commerce!");
//        Product pr = new Product("상품이름", 0, "설명", 0);
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
        int index = 1;
        // 1. 상품 목록 출력 - 실행하면 여러 전자제품을 출력해보자
        // 2. 제시된 상품 중 입력받은 숫자에 따라 다른 로직 실행
//        pr.getProducts().forEach(System.out::println);
        Scanner sc = new Scanner(System.in);

        while (true) {
            List<Product> productList = new ArrayList<>();
            productList.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 20));
            productList.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 15));
            productList.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 12));
            productList.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 2));

            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
            for (Product product : productList) {
                String formattedPrice = formatter.format(product.getPrice());
                System.out.printf("%d. %-15s | %10s원 | %s%n",
                        index++,
                        product.getItem(),
                        formattedPrice,
                        product.getDescription());
            }
            System.out.println("0. 종료");
            int exit = sc.nextInt();
            if (exit == 0) {
                break;
            }
        }
    }
}
