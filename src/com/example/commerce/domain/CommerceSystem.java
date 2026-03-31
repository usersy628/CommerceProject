package com.example.commerce.domain;

import java.text.NumberFormat;
import java.util.*;

public class CommerceSystem { // 프로그램 비즈니스 로직
    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
    Scanner sc = new Scanner(System.in);
    List<Category> categoryList = new ArrayList<>();
    List<Cart> cartList = new ArrayList<>();

    // 생성자 더미데이터
    public CommerceSystem() {
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

    public void start() {
        int totalPrice = 0;
        while (true) {
            printCategory();

            try {
                int categoryInput = sc.nextInt();
                if (categoryInput == 0) {
                    System.out.println("프로그램 종료");
                    break;
                }
                Category selectedCategory = categoryList.get(categoryInput - 1);

//                selectedCategory.printProductInfo();
                System.out.println("[ 실시간 커머스 플랫폼 - " + selectedCategory.getCategoryName() + " ]");
                List<Product> productList = selectedCategory.getProductList();
                for (int i = 0; i < productList.size(); i++) {
                    String formattedPrice = formatter.format(productList.get(i).getPrice());
                    System.out.printf("%d. %-15s | %10s원 | %s%n",
                            i + 1,
                            productList.get(i).getItem(),
                            formattedPrice,
                            productList.get(i).getDescription());
                }
                System.out.println("0. 뒤로가기");

                int productInput = sc.nextInt();
                if (productInput == 0) {
                    System.out.println("메뉴로 돌아가기");
                    continue;
                }
                // 상품 선택
                Product selectedProduct = productList.get(productInput - 1);
                String formattedPrice = formatter.format(selectedProduct.getPrice());
                System.out.printf("선택한 상품: %s | %s원 | %s | 재고: %d개%n",
                        selectedProduct.getItem(),
                        formattedPrice,
                        selectedProduct.getDescription(),
                        selectedProduct.getStock());

                System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인     2. 취소");

                // 카트에 담기
                if (sc.nextInt() == 1) {
                        cartList.stream()
                                .filter(cart ->cart.getProduct().getItem().equals(selectedProduct.getItem()))
                                .findFirst()
                                .ifPresentOrElse(
                                        cart -> cart.setQuantity(cart.getQuantity() + 1),
                                        () -> {
                                            cartList.add(new Cart(selectedProduct, 1));
                                        }
                                );
                        int prodNum = selectedProduct.getStock();
                        selectedProduct.setStock(selectedProduct.getStock() - 1);

                    System.out.println(selectedProduct.getItem() + "가 장바구니에 추가되었습니다.");
                    System.out.println("[ 주문관리 ]");
                    System.out.println("4. 장바구니 확인   | 장바구니를 확인 후 주문합니다");
                    System.out.println("5. 주문 취소   | 진행중인 주문을 취소합니다.");
                    int menu = sc.nextInt();
                    try {
                        switch (menu) {
                            case 4:
                                System.out.println("아래와 같이 주문하시겠습니까?");
                                System.out.println("[ 장바구니 내역 ]");
                                for (int i = 0; i < cartList.size(); i++) {
                                    formattedPrice = formatter.format(cartList.get(i).getProduct().getPrice());
                                    System.out.printf("선택한 상품: %s | %s원 | %s | 수량: %d개%n",
                                            cartList.get(i).getProduct().getItem(),
                                            formattedPrice,
                                            cartList.get(i).getProduct().getDescription(),
                                            cartList.get(i).getQuantity());
                                    totalPrice += cartList.get(i).getProduct().getPrice();
                                }

                                System.out.println("[ 총 주문 금액 ]");
                                String formattedTotalPrice = formatter.format(totalPrice);
                                System.out.println(formattedTotalPrice + "원");

                                System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
                                int order = sc.nextInt();
                                switch (order) {
                                    case 1:
                                        System.out.println("주문이 완료되었습니다! 총 금액: " + formattedTotalPrice + "원");
                                        for (int i = 0; i < cartList.size(); i++) {
                                            int cartNum = cartList.get(i).getQuantity(); // 카트수량
                                            // 이미 담을때 빼서
                                            cartList.get(i).getProduct().setStock( // 재고수량 - 장바구니수량
                                                    prodNum - cartList.get(i).getQuantity()
                                            );
                                            System.out.println(cartList.get(i).getProduct().getItem()
                                                    + " 재고가 "
                                                    + prodNum // 원래있던거
                                                    + " -> "
                                                    + cartList.get(i).getProduct().getStock() // 업데이트된거
                                                    + "개로 업데이트 되었습니다.");
                                        }
                                        totalPrice = 0;
                                        cartList.clear();
                                        break;
                                    case 2:
                                        break;
                                }
                            case 5:
                                continue;
                            default:
                                System.out.println("다시 입력하세요");;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch(IndexOutOfBoundsException e){
                System.out.println("등록된 상품이 없습니다");
            } catch(InputMismatchException e){
                System.out.println("번호를 입력해주세요");
                sc.nextLine();
            } catch(NullPointerException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void printCategory() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf("%d. %-15s%n",
                    i + 1,
                    categoryList.get(i).getCategoryName());
        }
        System.out.println("0. 종료");
    }
}