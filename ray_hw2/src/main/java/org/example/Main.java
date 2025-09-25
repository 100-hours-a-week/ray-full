package org.example;

import org.example.data.AssetInfo;
import org.example.data.EmployInfo;
import org.example.data.ItemInitializer;
import org.example.data.Printer;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    // 게임 시작 전
    private static final String START_GAME = "1";
    private static final String VIEW_DESCRIPTION = "2";

    // 게임 시작
    private static final String VIEW_MONEY = "1";
    private static final String VIEW_ASSETS = "2";
    private static final String BUY_BUILDING = "3";
    private static final String BUY_STOCK = "4";
    private static final String SELL_ASSET = "5";
    private static final String BUY_EMPLOYEE = "6";
    private static final String CHECK_EMPLOYEE = "7";
    private static final String EXIT_GAME = "8";

    public static void main(String[] args){
        String choice;
        // 게임 시작 전
        while (true){
            Printer.start();
            choice = sc.next();
            if(choice.equals(START_GAME)){
                break;
            }else if (choice.equals(VIEW_DESCRIPTION)){
                Printer.prolog();
            } else {
                System.out.println("올바르게 입력해주세요.");
            }
        }
        Me me = new Me();
        // 게임 시작
        while (true){
            Printer.choiceList();
            choice = sc.next();
            if(choice.equals(VIEW_MONEY)){
                me.printMoney();
            }else if (choice.equals(VIEW_ASSETS)){
                me.printAsset();
            }else if (choice.equals(BUY_BUILDING)){
                buyBuilding(me);
            }else if (choice.equals(BUY_STOCK)){
                buyStock(me);
            }else if(choice.equals(SELL_ASSET)){
                sellAsset(me);
            }else if(choice.equals(BUY_EMPLOYEE)){
                buyEmployee(me);
            }else if(choice.equals(CHECK_EMPLOYEE)){
                me.printEmployeeResult();
            }
            else if (choice.equals(EXIT_GAME)){
                me.printMoney();
                System.out.println("게임을 종료합니다.");
                break;
            }else {
                System.out.println("올바르게 입력해주세요.");
            }
        }
        return;
    }

    private static List<AssetInfo> buildingInfo = ItemInitializer.building();
    private static List<AssetInfo> stockInfo = ItemInitializer.stock();
    private static List<EmployInfo> employInfo = ItemInitializer.employ();

    private static void buyBuilding(Me me){
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

    private static void buyStock(Me me){
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

    private static void sellAsset(Me me){
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
    private static void buyEmployee(Me me){
        while (true){
            System.out.println("********************************************\n");
            System.out.println("고용 가능한 직원 목록입니다.\n");
            System.out.println("0. 구매 안함");
            int i  = 1;
            for (EmployInfo e: employInfo
            ) {
                System.out.print(i + ". ");
                e.printInfo();
                i++;
            }
            System.out.println("고용하실 직원을 선택해주세요.");
            String choice = sc.next();
            if(choice.equals("0")){
                System.out.println("직원 고용 종료");
                return;
            }else if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5")){
                me.buyEmployee(employInfo.get(Integer.valueOf(choice)-1), me);
            }else {
                System.out.println("올바르게 입력해주세요.");
            }
        }
    }
}
