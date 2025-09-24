package org.example.data;

public class Printer {
    public static void start(){
        System.out.println("********************************************");
        System.out.println("거지 게임!");
        System.out.println("********************************************\n");
        System.out.println("메뉴를 선택 해주세요.\n");
        System.out.println("1. 게임 시작 하기");
        System.out.println("2. 게임 설명 보기");
    }
    public static void prolog(){
        System.out.println("********************************************\n");
        System.out.println("게임 설명\n");
        System.out.println("거지 게임! 은 돈을 벌어 부자가 되는 게임입니다. 게임을 시작하면 자동으로 3초당 1원을 벌 수 있습니다.");
        System.out.println("추가 자산을 구매하여 더 빨리 돈을 벌어보세요!");
        System.out.println("건물 : 3초당 일정 가격이 올라가는 건물을 구매해보세요!");
        System.out.println("주식 : 3초당 일정 가격이 올라가는 주식을 구매해보세요! 단, 비밀스러운 확률로 가격이 떨어질 수 있습니다..\n");
    }
    public static void choiceList(){
        System.out.println("********************************************\n");
        System.out.println("메뉴를 선택 해주세요.\n");
        System.out.println("1. 내 잔액 보기");
        System.out.println("2. 내 자산 보기");
        System.out.println("3. 건물 구매하기");
        System.out.println("4. 주식 구매하기");
        System.out.println("5. 자산 판매하기");
        System.out.println("6. 게임 종료");
    }
}
