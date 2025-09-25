package org.example;

import org.example.data.EmployInfo;
import org.example.item.asset.Asset;
import org.example.item.asset.Building;
import org.example.item.asset.Stock;
import org.example.data.AssetInfo;
import org.example.item.employee.Employee;
import org.example.item.employee.EmployeeResult;

import java.util.*;

public class Me {
    private int money = 0;
    private int basicMakeMoney = 1;
    private List<Asset> myAsset = new ArrayList<>();

    private Queue<EmployeeResult> myEmployeeResult = new LinkedList<>();
    public Me(){
        Timer scheduler = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                money += basicMakeMoney;
            }
        };
        // 1초 후 3초마다 돈 벌기
        scheduler.scheduleAtFixedRate(task, 1000, 3000);
    }

    public void buyBuilding(AssetInfo assetInfo){
        if(assetInfo.price > money){
            System.out.println("돈이 부족합니다! 아직 거지군요..");
            return;
        }
        Building building = new Building(assetInfo.name, assetInfo.price, assetInfo.change);
        myAsset.add(building);
        takeMoney(assetInfo.price);
        System.out.println("빌딩 구매 성공!");
    }
    public void buyStock(AssetInfo assetInfo){
        if(assetInfo.price > money){
            System.out.println("돈이 부족합니다! 아직 거지군요..");
            return;
        }
        Stock stock = new Stock(assetInfo.change);
        myAsset.add(stock);
        takeMoney(assetInfo.price);
        System.out.println("주식 구매 성공!");
    }
    public void sellAsset(int n){
        Asset asset = myAsset.get(n);
        makeMoney(asset.getPrice());
        System.out.println("자산 판매 성공!");
        myAsset.remove(n);
    }

    public void takeMoney(int n){
        money -= n;
    }
    public void makeMoney(int n){
        money += n;
    }
    public void printMoney(){
        System.out.println("현재 잔액은 " + money + "원 입니다.");
    }

    public void printAsset(){
        if (myAsset.size() == 0){
            System.out.println("구매한 자산이 없습니다! 자산을 구매 해보세요.");
            return;
        }
        int i = 1;
        for (Asset a: myAsset
             ) {
            System.out.print(i+". ");
            a.printInfo();
            i++;
        }
    }

    public void buyEmployee(EmployInfo employInfo, Me me){
        if(employInfo.getPrice() > money){
            System.out.println("돈이 부족합니다! 아직 거지군요..");
            return;
        }

        Thread employeeThread = new Thread(new Employee(employInfo, me));
        employeeThread.start();
        takeMoney(employInfo.getPrice());
        System.out.println("직원 고용 성공! 직원이 일을 하러 떠납니다.. " + employInfo.getWorkTime() + "초 후 벌어온 돈을 확인하세요.");
    }

    public void printEmployeeResult(){
        if (myEmployeeResult.isEmpty()){
            System.out.println("아직 돈을 벌어온 직원이 없습니다..");
        }
        while (!myEmployeeResult.isEmpty()) {
            EmployeeResult employeeResult = myEmployeeResult.poll();
            System.out.println(employeeResult.getName() + " 직원이 " + employeeResult.getResult());
            makeMoney(employeeResult.getTotal());
        }
    }

    public void addEmployeeResult(String name, String result, int total){
        myEmployeeResult.add(new EmployeeResult(name, result, total));
    }
}
