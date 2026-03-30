package com.example.commerce.domain;

import java.text.NumberFormat;
import java.util.*;

public class CommerceSystem { // 프로그램 비즈니스 로직
    //커머스 플랫폼의 상품 관리 / 사용자 입력을 처리하기
    // 1. 상품 목록 출력 - 실행하면 여러 전자제품을 출력해보자
    // 2. 제시된 상품 중 입력받은 숫자에 따라 다른 로직 실행

    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
    Product product = new Product("", 0, "", 0);
    Category category = new Category("");
    List<Category> categoryList = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public CommerceSystem() {
        this.categoryList = new ArrayList<>();
    }

    public void start() {
        category.initData();
        while (true) {
//            int index = 1;
            category.viewCategory();
            System.out.println("0. 종료");

            System.out.println("categoryList.toString() - " + category.categoryList.toString());
//            [전자제품, 의류, 식품]

            try {
                int input = sc.nextInt();
                if (input == 0) {
                    System.out.println("프로그램 종료");
                    break;
                }
                Category selectedCategory = category.categoryList.get(input - 1);
//                System.out.println(" 00000 " + selectedCategory);

                List <Product> selectedProductList = category.viewProductList(selectedCategory);
//                System.out.println(" 22222 " + selectedProductList);
                System.out.println("0. 뒤로가기");

                int select = sc.nextInt();
                if (select == 0) {
                    System.out.println("메뉴로 돌아가기");
                    continue;
                }
                // 상품 선택
                Product selectedProduct = selectedProductList.get(select - 1);
                category.viewProduct(selectedProduct);
                System.out.println("3333 " + selectedProduct);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("등록된 상품이 없습니다");
            } catch (InputMismatchException e) {
                System.out.println("번호를 입력해주세요");
                sc.nextLine();
            }
        }
    }
}