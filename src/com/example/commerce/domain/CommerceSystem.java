package com.example.commerce.domain;

import java.text.NumberFormat;
import java.util.*;

public class CommerceSystem { // 프로그램 비즈니스 로직
    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
    Product product;
    Category category = new Category("");
    List<Category> categoryList;

    Scanner sc = new Scanner(System.in);

    public CommerceSystem() {
        this.categoryList = new ArrayList<>();
        category.initData();
    }

    public void start() {
        while (true) {
            category.viewCategory();
            System.out.println("0. 종료");

            try {
                int categoryInput = sc.nextInt();
                if (categoryInput == 0) {
                    System.out.println("프로그램 종료");
                    break;
                }
                Category selectedCategory = category.findCategory(categoryInput);

                List <Product> selectedProductList = category.viewProductList(selectedCategory);
                System.out.println("0. 뒤로가기");

                int productInput = sc.nextInt();
                if (productInput == 0) {
                    System.out.println("메뉴로 돌아가기");
                    continue;
                }
                // 상품 선택
                Product selectedProduct = selectedProductList.get(productInput);
                category.viewProduct(selectedProduct);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("등록된 상품이 없습니다");
            } catch (InputMismatchException e) {
                System.out.println("번호를 입력해주세요");
                sc.nextLine();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}