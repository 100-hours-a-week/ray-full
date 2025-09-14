package org.example;

import org.example.asset.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);



    public static void main(String[] args){
        String choice;
        // 게임 시작 전
        while (true){
            printStart();
            choice = sc.next();
            if(choice.equals("1")){
                break;
            }else if (choice.equals("2")){
                printDescription();
            } else {
                System.out.println("올바르게 입력해주세요.");
            }
        }
        Me me = new Me();
        initInfo();
        // 게임 시작
        while (true){
            printChoiceList();
            choice = sc.next();
            if(choice.equals("1")){
                me.printMoney();
            }else if (choice.equals("2")){
                me.printAsset();
            }else if (choice.equals("3")){
                choice3(me);
            }else if (choice.equals("4")){
                choice4(me);
            }else if(choice.equals("5")){
                choice5(me);
            }else if (choice.equals("6")){
                me.printMoney();
                System.out.println("게임을 종료합니다.");
                break;
            }else {
                System.out.println("올바르게 입력해주세요.");
            }
        }
        return;
    }
    private static void printStart(){
        System.out.println("********************************************");
        System.out.println("거지 게임!");
        System.out.println("********************************************\n");
        System.out.println("메뉴를 선택 해주세요.\n");
        System.out.println("1. 게임 시작 하기");
        System.out.println("2. 게임 설명 보기");
    }
    private static void printDescription(){
        System.out.println("********************************************\n");
        System.out.println("게임 설명\n");
        System.out.println("거지 게임! 은 돈을 벌어 부자가 되는 게임입니다. 게임을 시작하면 자동으로 3초당 1원을 벌 수 있습니다.");
        System.out.println("추가 자산을 구매하여 더 빨리 돈을 벌어보세요!");
        System.out.println("건물 : 3초당 일정 가격이 올라가는 건물을 구매해보세요!");
        System.out.println("주식 : 3초당 일정 가격이 올라가는 주식을 구매해보세요! 단, 비밀스러운 확률로 가격이 떨어질 수 있습니다..\n");
    }
    private static void printChoiceList(){
        System.out.println("********************************************\n");
        System.out.println("메뉴를 선택 해주세요.\n");
        System.out.println("1. 내 잔액 보기");
        System.out.println("2. 내 자산 보기");
        System.out.println("3. 건물 구매하기");
        System.out.println("4. 주식 구매하기");
        System.out.println("5. 자산 판매하기");
        System.out.println("6. 게임 종료");
    }
    private static List<AssetInfo> buildingInfo = new ArrayList<>();
    private static List<AssetInfo> stockInfo = new ArrayList<>();
    private static void initInfo(){
        buildingInfo.add(new AssetInfo("우리집 뒷마당 컨테이너", 10, 1));
        buildingInfo.add(new AssetInfo("우리집 앞마당 빌딩", 100, 5));
        buildingInfo.add(new AssetInfo("63빌딩", 500, 10));
        buildingInfo.add(new AssetInfo("롯데월드타워", 1000, 20));
        buildingInfo.add(new AssetInfo("구글 본사 건물", 5000, 50));

        stockInfo.add(new AssetInfo("빙그래", 20, 6));
        stockInfo.add(new AssetInfo("하이트진로", 200, 30));
        stockInfo.add(new AssetInfo("삼성전자", 1000, 60));
        stockInfo.add(new AssetInfo("테슬라", 2000, 120));
        stockInfo.add(new AssetInfo("버크셔 해서웨이 A주", 10000, 500));
    }

    private static void choice3(Me me){
        while (true){
            System.out.println("********************************************\n");
            System.out.println("구매 가능한 건물 목록입니다.\n");
            System.out.println("0. 구매 안함");
            int i  = 1;
            for (AssetInfo a: buildingInfo
            ) {
                System.out.print(i + ". ");
                a.printInfo();
                i++;
            }
            System.out.println("구매하실 건물을 선택해주세요.");
            String choice = sc.next();
            if(choice.equals("0")){
                System.out.println("건물 구매 종료");
                return;
            }else if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5")){

                me.buyBuilding(buildingInfo.get(Integer.valueOf(choice)-1));
            }else {
                System.out.println("올바르게 입력해주세요.");
            }
        }

    }

    private static void choice4(Me me){
        while (true){
            System.out.println("********************************************\n");
            System.out.println("구매 가능한 주식 목록입니다.\n");
            System.out.println("0. 구매 안함");
            int i  = 1;
            for (AssetInfo a: stockInfo
            ) {
                System.out.print(i + ". ");
                a.printInfo();
                i++;
            }
            System.out.println("구매하실 주식을 선택해주세요.");
            String choice = sc.next();
            if(choice.equals("0")){
                System.out.println("주식 구매 종료");
                return;
            }else if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5")){
                me.buyStock(stockInfo.get(Integer.valueOf(choice)-1));
            }else {
                System.out.println("올바르게 입력해주세요.");
            }
        }

    }

    private static void choice5(Me me){
        while (true){
            System.out.println("********************************************\n");
            System.out.println("판매 가능한 자산 목록입니다.\n");
            System.out.println("0. 판매 안함");
            me.printAsset();
            System.out.println("판매하실 자산을 선택해주세요.");
            String choice = sc.next();
            if(choice.equals("0")){
                System.out.println("자산 판매 종료");
                return;
            }else{
                me.sellAsset(Integer.valueOf(choice)-1);
            }
        }


    }
}
