package com.example.commerce.domain;

import java.text.NumberFormat;
import java.util.*;

public class CommerceSystem {
    /**
    *    프로그램 비즈니스 로직
    */
    NumberFormat formatter = NumberFormat.getNumberInstance(Locale.KOREA);
    Scanner sc = new Scanner(System.in);
    List<Category> categoryList = new ArrayList<>();
    List<Cart> cartList = new ArrayList<>();

    public CommerceSystem() {
        initData();
    }

    public void start() {
        while (true) {
            try {
                printCategory();
                int categoryInput = sc.nextInt();
                if (categoryInput == 0) {
                    System.out.println("프로그램 종료");
                    break;
                }
                Category selectedCategory = categoryList.get(categoryInput - 1);
                selectedCategory.printProductInfo();

                int productInput = sc.nextInt();
                if (productInput == 0) {
                    System.out.println("메뉴로 돌아가기");
                    return;
                }
                Product selectedProduct = selectedCategory.getProductList().get(productInput - 1);
                selectedProduct.printSelectedProduct();
                askAddToCart();
                if (sc.nextInt() == 1) {
                    addCartItem(selectedProduct);
                    showOrderProcess();
                    try {
                        showOrderMenu();
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

    // 더미데이터
    private void initData() {
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
    }

    // 메인 카테고리 출력
    private void printCategory() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf("%d. %-15s%n",
                    i + 1,
                    categoryList.get(i).getCategoryName());
        }
        System.out.println("0. 종료");
    }

    // 장바구니 추가 분기
    private void askAddToCart() {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인     2. 취소");
    }

    // 장바구니 추가
    private void addCartItem(Product selectedProduct) {
        cartList.stream()
                .filter(cart ->cart.getProduct().getItem().equals(selectedProduct.getItem()))
                .findFirst()
                .ifPresentOrElse(
                        cart -> cart.setQuantity(cart.getQuantity() + 1),
                        () -> { cartList.add(new Cart(selectedProduct, 1)); });
        selectedProduct.setStock(selectedProduct.getStock() - 1);
        System.out.println(selectedProduct.getItem() + "가 장바구니에 추가되었습니다.");
    }

    // 주문관리 프로세스
    private void showOrderProcess() {
        System.out.println("[ 주문관리 ]");
        System.out.println("4. 장바구니 확인   | 장바구니를 확인 후 주문합니다");
        System.out.println("5. 주문 취소   | 진행중인 주문을 취소합니다.");
        showOrderMenu();
    }

    // 주문관리 메뉴
    private void showOrderMenu() {
        int menu = sc.nextInt();
        switch (menu) {
            case 4:
                askOrder();
                int order = sc.nextInt();
                switch (order) {
                    case 1:
                        confirmOrder();
                        break;
                    case 2:
                        break;
                }
                break;
            case 5:
                break;
            default: System.out.println("다시 입력하세요");;
        }
    }

    // 주문하기 분기
    private void askOrder() {
        System.out.println("아래와 같이 주문하시겠습니까?");
        printCartList();
        System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
    }

    // 장바구니 출력
    private void printCartList() {
        System.out.println("[ 장바구니 내역 ]");
        for (int i = 0; i < cartList.size(); i++) {
            String formattedPrice = formatter.format(cartList.get(i).getProduct().getPrice());
            System.out.printf("선택한 상품: %s | %s원 | %s | 수량: %d개%n",
                    cartList.get(i).getProduct().getItem(),
                    formattedPrice,
                    cartList.get(i).getProduct().getDescription(),
                    cartList.get(i).getQuantity());
        }
        System.out.println("[ 총 주문 금액 ]");
        System.out.println(formatter.format(calculateTotalPrice()) + "원");
    }

    // 주문 확정
    private void confirmOrder() {
        int totalPrice = calculateTotalPrice();
        System.out.println("주문이 완료되었습니다! 총 금액: " + formatter.format(totalPrice) + "원");
        for (int i = 0; i < cartList.size(); i++) {
            Product productInCart = cartList.get(i).getProduct();
            // 이미 담을때 빼서
            System.out.println(productInCart.getItem()
                    + " 재고가 "
                    + (productInCart.getStock() + cartList.get(i).getQuantity())  // 원래있던거
                    + " -> "
                    + productInCart.getStock() // 업데이트된거
                    + "개로 업데이트 되었습니다.");
        }
        calculateTotalPrice();
        cartList.clear();
    }

    // 총 금액 계산
    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < cartList.size(); i++) {
            totalPrice += cartList.get(i).getProduct().getPrice() * cartList.get(i).getQuantity();
        }
        return totalPrice;
    }
}